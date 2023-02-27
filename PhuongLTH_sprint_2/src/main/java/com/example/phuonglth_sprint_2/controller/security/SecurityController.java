package com.example.phuonglth_sprint_2.controller.security;

import com.example.phuonglth_sprint_2.dto.avatar.ChangeAvatarDto;
import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
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
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {

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

    public SecurityController(SendMail sendMail, IAccountService accountService, IRoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider, JwtTokenFilter jwtTokenFilter, ICustomerService customerService, IEmployeeService employeeService) {
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
        if (!customerService.checkMail(customerDto.getEmail())){
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            Account account = new Account();
            account.setName(customerDto.getName());
            account.setEmail(customerDto.getEmail());
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
        }else {
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
        Optional<Customer> customer = customerService.findByEmail(signInForm.getEmail());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getAuthorities(),
                accountPrinciple.getId(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                customer));
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
}
