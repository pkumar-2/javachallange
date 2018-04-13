package com.db.awmd.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Account {

  public String getAccountId() {
    return accountId;
  }

  @NotNull
  @javax.validation.constraints.NotBlank
  private final String accountId;

  @NotNull
  @Min(value = 0, message = "Initial balance must be positive.")
  private BigDecimal balance;

  public Account(String accountId) {
    this.accountId = accountId;
    this.balance = BigDecimal.ZERO;
  }

  @JsonCreator
  public Account(@JsonProperty("accountId") String accountId,
    @JsonProperty("balance") BigDecimal balance) {
    this.accountId = accountId;
    this.balance = balance;
  }




/*
  public synchronized void withdraw(BigDecimal bal) {
    try {

      if (balance.compareTo(bal)>0) {
        System.out.println(accountId + " " + "is try to withdraw");
        try {
          Thread.sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }
        balance.add(bal);
        System.out.println(accountId + " " + "is complete the withdraw");
      } else {
        System.out.println(accountId + " " + "doesn't have enough money for withdraw ");
      }
      System.out.println(accountId + " " + " withdraw Rs." + balance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public synchronized void deposit(BigDecimal bal) {
    try {
      if (bal.compareTo(BigDecimal.valueOf(0))>0) {
        System.out.println(accountId + " " + " is try to deposit");
        try {
          Thread.sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }
        balance.add(bal);
        System.out.println(accountId + " " + "is complete the deposit");
      } else {
        System.out.println(accountId + " " + "doesn't have enough money for deposit");
      }
      System.out.println(accountId + " " + " deposit Rs." + balance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
