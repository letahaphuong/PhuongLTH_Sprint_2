package com.example.phuonglth_sprint_2.service.account;


import com.example.phuonglth_sprint_2.entity.account.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    Account save(Account account);
}
