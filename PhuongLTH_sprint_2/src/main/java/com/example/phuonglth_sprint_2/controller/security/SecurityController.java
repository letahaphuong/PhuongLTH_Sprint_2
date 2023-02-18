package com.example.phuonglth_sprint_2.controller.security;

import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
import com.example.phuonglth_sprint_2.dto.request.SignInForm;
import com.example.phuonglth_sprint_2.dto.response.JwtResponse;
import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.account.Role;
import com.example.phuonglth_sprint_2.entity.account.RoleName;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.jwt.jwt.JwtProvider;
import com.example.phuonglth_sprint_2.jwt.jwt.JwtTokenFilter;
import com.example.phuonglth_sprint_2.jwt.userprincal.AccountPrinciple;
import com.example.phuonglth_sprint_2.service.ICustomerService;
import com.example.phuonglth_sprint_2.service.IRoleService;
import com.example.phuonglth_sprint_2.service.impl.AccountService;
import com.example.phuonglth_sprint_2.service.impl.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {

    @Autowired
    AccountService accountService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    private ICustomerService customerService;

    @PostMapping(value = "/signup")
    public ResponseEntity<Customer> register(@Valid @RequestBody CustomerDto customerDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Customer>((Customer) bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        Account account = new Account();
        account.setName(customerDto.getName());
        account.setEmail(customerDto.getEmail());
        account.setAvatar("helloo");
        account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.USER).orElse(new Role());
        roles.add(customerRole);
        account.setRoles(roles);
        accountService.save(account);
        customer.setAccount(account);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar()));
    }
}
