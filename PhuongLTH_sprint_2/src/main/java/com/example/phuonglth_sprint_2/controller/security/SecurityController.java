package com.example.phuonglth_sprint_2.controller.security;

import com.example.phuonglth_sprint_2.dto.account.TokenDto;
import com.example.phuonglth_sprint_2.dto.avatar.ChangeAvatarDto;
import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
import com.example.phuonglth_sprint_2.dto.customer.GetIdCustomerView;
import com.example.phuonglth_sprint_2.dto.employee.EmployeeDto;
import com.example.phuonglth_sprint_2.dto.request.SignInForm;
import com.example.phuonglth_sprint_2.dto.response.JwtResponse;
import com.example.phuonglth_sprint_2.dto.response.ResponseMessage;
import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.account.Role;
import com.example.phuonglth_sprint_2.entity.account.RoleName;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.employee.Employee;
import com.example.phuonglth_sprint_2.jwt.jwt.JwtProvider;
import com.example.phuonglth_sprint_2.jwt.jwt.JwtTokenFilter;
import com.example.phuonglth_sprint_2.jwt.userprincal.AccountPrinciple;
import com.example.phuonglth_sprint_2.service.account.IAccountService;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import com.example.phuonglth_sprint_2.service.account.IRoleService;
import com.example.phuonglth_sprint_2.service.employee.IEmployeeService;
import com.example.phuonglth_sprint_2.service.send_mail.SendMail;
import com.example.phuonglth_sprint_2.service.valid.IValidService;
import com.example.phuonglth_sprint_2.service.valid.impl.ValidService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    @Value("${google.clientId}")
    String googleClientId;
    @Value("${secretPsw}")
    String secretPsw;

    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private final
    IAccountService accountService;

    private final
    IRoleService roleService;

    private final
    PasswordEncoder passwordEncoder;

    private final
    AuthenticationManager authenticationManager;

    private final
    JwtProvider jwtProvider;

    private final
    JwtTokenFilter jwtTokenFilter;

    private final ICustomerService customerService;

    private final IEmployeeService employeeService;

    private final SendMail sendMail;

    private final IValidService validService;

    public SecurityController(SendMail sendMail, IAccountService accountService, IRoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider, JwtTokenFilter jwtTokenFilter, ICustomerService customerService, IEmployeeService employeeService, IValidService validService) {
        this.validService = validService;
        this.accountService = accountService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.jwtTokenFilter = jwtTokenFilter;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.sendMail = sendMail;
    }

    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDto tokenDto) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        Account account = new Account();
        Customer customer = new Customer();
        if (accountService.existsByEmail(payload.getEmail())) {
            account = accountService.findByEmail(payload.getEmail()).get();
        } else {
            account.setEmail(payload.getEmail());
            account.setAnony("1");
            account.setEncryptPassword(passwordEncoder.encode(secretPsw));
            accountService.save(account);
            account = saveAccount(account);
            Optional<Account> accountOptional = accountService.findByEmail(payload.getEmail());
            customer.setAccount(accountOptional.get());
            customer.setEmail(payload.getEmail());
            customer.setAnony(1);
            customerService.save(customer);
        }
        ResponseEntity<?> tokenRes = loginOauth(account);
        return new ResponseEntity<>(tokenRes, HttpStatus.OK);
    }

    @PostMapping("/facebook")
    public ResponseEntity<?> facebook(@RequestBody TokenDto tokenDto) {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        final String[] fields = {"email", "picture", "name", "gender"};
        User user = facebook.fetchObject("me", User.class, fields);
        Account account = new Account();
        Customer customer = new Customer();
        if (accountService.existsByEmail(user.getEmail())) {
            account = accountService.findByEmail(user.getEmail()).get();
        } else {
            account.setName(user.getName());
            account.setEmail(user.getEmail());
            account.setEncryptPassword(passwordEncoder.encode(secretPsw));
            account.setAnony("1");
            account = saveAccount(account);
            Optional<Account> accountOptional = accountService.findByEmail(user.getEmail());
            customer.setAccount(accountOptional.get());
            customer.setEmail(user.getEmail());
            customer.setName(user.getName());
            customer.setAnony(1);
            customerService.save(customer);
        }
        ResponseEntity<?> tokenRes = loginOauth(account);
        return new ResponseEntity<>(tokenRes, HttpStatus.OK);
    }

    private ResponseEntity<?> loginOauth(Account account) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getEmail(), secretPsw)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Optional<GetIdCustomerView> idCustomer = customerService.getIdCustomer(account.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt,
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                idCustomer,
                accountPrinciple.getAnony()));
    }

    private Account saveAccount(Account account) {
        accountService.save(account);
        Role rolUser = roleService.findByName(RoleName.USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(rolUser);
        account.setRoles(roles);
        return accountService.save(account);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerDto customerDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
//        if (accountService.existsByEmail(customerDto.getEmail())) {
//            return new ResponseEntity<>(new ResponseMessage("Email đã được đăng ký."), HttpStatus.CONFLICT);
//        }
        if (!customerService.checkMail(customerDto.getEmail())) {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            Account account = new Account();
            account.setName(customerDto.getName());
            account.setEmail(customerDto.getEmail());
            account.setAnony("1");
            if (customerDto.getAvatar() == null || customerDto.getAvatar().trim().isEmpty()) {
                account.setAvatar("https://scontent.fdad1-2.fna.fbcdn.net/v/t39.30808-6/192275406_2914709508745440_8981882595411494044_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=9zAXeOiPSG0AX--Zoj9&_nc_ht=scontent.fdad1-2.fna&oh=00_AfCWcgq7aOxNzbyKJGkuzqVSUGoLykco7Mv8XThFV22ElA&oe=63F5DC03");

            }
            account.setEncryptPassword(passwordEncoder.encode(customerDto.getEncryptPassword()));
            Set<Role> roles = new HashSet<>();
            Role customerRole = roleService.findByName(RoleName.USER).orElse(new Role());
            roles.add(customerRole);
            account.setRoles(roles);
            accountService.save(account);
            customer.setAccount(account);
            customerService.save(customer);
            sendMail.SendEmailToCustomer(customerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) authentication.getPrincipal();
        Optional<GetIdCustomerView> idCustomer = customerService.getIdCustomer(signInForm.getEmail());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                idCustomer,
                accountPrinciple.getAnony()));
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatarDto changeAvatarDto) {
        String jwt = jwtTokenFilter.getJwt(request);
        String email = jwtProvider.getUserNameFromToken(jwt);
        Account account;
        try {
            if (changeAvatarDto.getAvatar() == null) {
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.ACCEPTED);
            } else {
                account = accountService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email không tồn tại" + email));
                account.setAvatar(changeAvatarDto.getAvatar());
                accountService.save(account);
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/signup-employee")
    public ResponseEntity<?> register(@Valid @RequestBody EmployeeDto employeeDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(),
                    HttpStatus.BAD_REQUEST);
        }
        if (accountService.existsByEmail(employeeDto.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã được đăng ký."), HttpStatus.ACCEPTED);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        Account account = new Account();
        account.setName(employeeDto.getName());
        account.setEmail(employeeDto.getEmail());
        account.setAnony("2");
        if (employeeDto.getAvatar() == null || employeeDto.getAvatar().trim().isEmpty()) {
            account.setAvatar("https://scontent.fdad1-2.fna.fbcdn.net/v/t39.30808-6/192275406_2914709508745440_8981882595411494044_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=9zAXeOiPSG0AX--Zoj9&_nc_ht=scontent.fdad1-2.fna&oh=00_AfCWcgq7aOxNzbyKJGkuzqVSUGoLykco7Mv8XThFV22ElA&oe=63F5DC03");

        }
        account.setEncryptPassword(passwordEncoder.encode(employeeDto.getEncryptPassword()));
        Set<Role> roles = new HashSet<>();
        Role customerRole = roleService.findByName(RoleName.ADMIN).orElse(new Role());
        roles.add(customerRole);
        account.setRoles(roles);
        accountService.save(account);
        employee.setAccount(account);
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("check-valid/{value}")
    public ResponseEntity<?> checkValid(@PathVariable("value") String value) {
        Boolean checkEmail = validService.existsByEmail(value);
        if (!checkEmail) {
            return new ResponseEntity<>(new ResponseMessage("ok"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Thông tin đã tồn tại. Vui lòng nhập lại."), HttpStatus.CREATED);
        }
    }
}
