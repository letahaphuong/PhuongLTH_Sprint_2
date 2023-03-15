package com.example.phuonglth_sprint_2.repository.account;

import com.example.phuonglth_sprint_2.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    Long getLastInsertId();
}

