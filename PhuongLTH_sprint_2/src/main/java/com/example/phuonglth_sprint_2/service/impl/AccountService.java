package com.example.phuonglth_sprint_2.service.impl;

import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.repository.IAccountRepository;
import com.example.phuonglth_sprint_2.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
