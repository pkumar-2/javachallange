package com.db.awmd.challenge;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class DevChallengeApplication {
  private static final String STARTUP_HEALTH_CHECK="Application started, beginning health check!";
  private static final String STARTUP_HEALTH_DOWN = "Health is DOWN, application will shutdown!";

  private static final String STARTUP_HEALTH_UP ="Health is UP, finished application check!";
  private static final Logger LOGGER = LoggerFactory.getLogger(DevChallengeApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DevChallengeApplication.class, args);
  }



  /**
   * This will call the application's {@link HealthEndpoint} to retrieve the health status. If the overall health status is not 'UP', it will
   * 1. print an error providing the health resource details
   * 2. shutdown the application
   *
   * @param applicationContext the context to validate against
   */
  static void checkApplicationHealth(ConfigurableApplicationContext applicationContext) {
    ConfigurableEnvironment env = applicationContext.getEnvironment();
    ThreadContext.put("ACTIVE_PROFILE", env.getProperty("spring.profiles.active", "dev"));
    ThreadContext.put("APP_VERSION", env.getProperty("application.build.version", "0.0.0-default"));

    // check if system health is UP, otherwise shutdown
    LOGGER.info(STARTUP_HEALTH_CHECK);
    Health systemHealth = applicationContext.getBean(HealthEndpoint.class).health();
    if (Status.DOWN.equals(systemHealth.getStatus())) {
      ThreadContext.put("ERROR_DETAILS", systemHealth.toString());
      LOGGER.error(STARTUP_HEALTH_DOWN);
      ThreadContext.remove("ERROR_DETAILS");
      applicationContext.close();
      return;
    }
    LOGGER.info(STARTUP_HEALTH_UP);
    ThreadContext.clearAll();
  }


}
