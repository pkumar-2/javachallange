package com.db.awmd.challenge.service;

import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Getter
    private final AccountsRepository accountsRepository;

    @Autowired
    public TransferService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    public AccountsRepository getAccountsRepository() {
        return accountsRepository;
    }
}
