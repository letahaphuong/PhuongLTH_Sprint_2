import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SecurityService} from "../service/security.service";
import {TokenService} from "../service/token.service";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  statusRole: any[] = [];

  signInForm: FormGroup = new FormGroup({});

  constructor(private securityService: SecurityService,
              private tokenService: TokenService,
              private router: Router,
              private toast: ToastrService,
              private formBuilder: FormBuilder,
              private title: Title) {
    this.title.setTitle('Trang đăng nhập');
  }

  ngOnInit(): void {
    this.getFormLogin();
  }

  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      rememberMe: [false]
    });
  }

  future(): void {
    this.toast.info('Chức năng đăng đang được xây dựng.');
  }

  login(): void {
    const signInForm = this.signInForm?.value;
    console.log(this.signInForm);
    this.securityService.signIn(signInForm).subscribe(data => {
        if (data.token !== undefined) {
          if (this.signInForm?.value.rememberMe) {
            this.tokenService.rememberMe(data.roles, data.name, data.token);
            location.href = 'http://localhost:4200/home';
          } else {
            this.tokenService.setToken(data.token);
            this.tokenService.setName(data.name);
            this.tokenService.setRole(data.roles);
            this.statusRole = data.roles;
            this.tokenService.setEmail(data.email);
            this.tokenService.setId(data.id);
            this.tokenService.setAvatar(data.avatar);
            location.href = 'http://localhost:4200/home';
            this.toast.info('Đăng nhập thành công.', 'Thông báo', {
              timeOut: 3000
            });
          }
        }
        // @ts-ignore
        if (data.status === 202) {
          this.toast.error('Mật khẩu không đúng vui lòng nhập lại.', 'Thông báo', {
            timeOut: 3000,
            extendedTimeOut: 1500
          });
        }
      }, error => {
        if (error.status === 403) {
          this.toast.error('Đăng nhập thất bại, vui lòng nhập lại.', 'Thông báo')
        }
      }
    )
    ;
  }
}
