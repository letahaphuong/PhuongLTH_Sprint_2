package com.example.phuonglth_sprint_2.jwt.userprincal;

import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {

    @Autowired
    IAccountRepository accountRepository;

    @Override// tìm account có tồn tại trong DB không
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("Account không tìm thấy -> email hoặc passwword" + username));
        return AccountPrinciple.build(account); // gọi lại hàm build bên Accountprinciple để build account mới
    }
}
