import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SecurityService} from '../service/security.service';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {EncryInforService} from '../service/encry-infor.service';
// @ts-ignore
import * as CryptoJS from 'crypto-js';
import {FacebookLoginProvider, GoogleLoginProvider, SocialAuthService, SocialUser} from 'angularx-social-login';
import {TokenDto} from '../../dto/token/TokenDto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  socialUser: SocialUser | undefined;
  userLogged: SocialUser | undefined;
  isLogged: boolean | undefined;
  checkLoading = false;

  constructor(private securityService: SecurityService,
              private tokenService: TokenService,
              private router: Router,
              private toast: ToastrService,
              private formBuilder: FormBuilder,
              private title: Title,
              private encryInforService: EncryInforService,
              private authService: SocialAuthService
  ) {
    this.title.setTitle('Trang đăng nhập');
  }

  signInForm: FormGroup = new FormGroup({});

  ngOnInit(): void {
    this.getFormLogin();
    this.authService.authState.subscribe(data => {
      this.userLogged = data;
      this.isLogged = (this.userLogged != null && this.tokenService.getToken() != null);
    });
  }

  signInWithGoogle(): void {
    this.checkLoading = true;
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(data => {
      this.socialUser = data;
      const tokenGoogle = new TokenDto(this.socialUser.idToken);
      console.log(tokenGoogle);
      this.securityService.google(tokenGoogle).subscribe(
        res => {
          // @ts-ignore
          this.tokenService.setAnony(res.body.anony);
          // @ts-ignore
          this.tokenService.setId(res.body.id);
          // @ts-ignore
          this.tokenService.setIdCustomer(res.body.idCustomer.idCustomer);
          // @ts-ignore
          this.tokenService.setToken(res.body.token);
          // @ts-ignore
          this.tokenService.setEmail(res.body.email);
          this.isLogged = true;
          location.href = 'http://localhost:4200/home';
          this.toast.success('Đăng nhập thành công.');
        }, error => {
          this.toast.error('Đăng nhập không thành công.');
          console.log(error);
          this.checkLoading = false;
        }
      );
    }).catch(error => {
      console.log(error);
    });
  }

  signInWithFB(): void {
    this.checkLoading = true;
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
      this.socialUser = data;
      const tokenFace = new TokenDto(this.socialUser.authToken);
      this.securityService.facebook(tokenFace).subscribe(res => {
        console.log(res);
        console.log(res.body.id);
        this.tokenService.setAnony(res.body.anony);
        this.tokenService.setId(res.body.id);
        this.tokenService.setIdCustomer(res.body.idCustomer.idCustomer);
        this.tokenService.setToken(res.body.token);
        this.tokenService.setEmail(res.body.email);
        this.isLogged = true;
        location.href = 'http://localhost:4200/home';
        this.toast.success('Đăng nhập thành công.');
        this.checkLoading = false;
      }, error => {
        this.checkLoading = false;
        this.toast.error('Đăng nhập thất bại.');
        console.log(error);
        this.tokenService.logOut();
      });
    }).catch(
      error => {
        console.log(error);
      }
    );
  }

  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(150)]],
      rememberMe: [false]
    });
  }

  future(): void {
    this.toast.info('Chức năng đăng đang được xây dựng.');
  }

  login(): void {
    this.checkLoading = true;
    const signInForm = this.signInForm?.value;
    this.securityService.signIn(signInForm).subscribe(data => {
        if (data.token !== undefined) {
          if (this.signInForm?.value.rememberMe) {
            this.tokenService.rememberMe(data.roles, data.name, data.token, data.avatar, data.email, data.id);
            location.href = 'http://localhost:4200/home';
            this.toast.info('Đăng nhập thành công.', 'Thông báo', {
              timeOut: 3000
            });
          } else {
            this.tokenService.setName(data.name);
            // this.tokenService.setName(CryptoJS.AES.encrypt(data.name.trim(), this.encPassword?.trim()).toString());
            this.tokenService.setToken(data.token);
            // this.tokenService.setName(data.name);
            // this.tokenService.setRole(data.roles);
            // this.statusRole = data.roles;
            this.tokenService.setEmail(data.email);
            this.tokenService.setAnony(data.anony);
            this.tokenService.setId(data.id);
            this.tokenService.setAvatar(data.avatar);
            if (data.anony === '1') {
              this.tokenService.setIdCustomer(data.idCustomer.idCustomer);
            } else {
              this.tokenService.setIdCustomer('U2FsdGVkX19BqfaXV3kWCAGzLozWDI1h9w39jmAb14g=');
            }
            location.href = 'http://localhost:4200/home';
            this.toast.info('Đăng nhập thành công.', 'Thông báo', {
              timeOut: 3000,
              extendedTimeOut: 3000
            });
          }
        }
        // @ts-ignore
        if (data.status === 202) {
          this.toast.error('Mật khẩu không đúng vui lòng nhập lại.', 'Thông báo', {
            timeOut: 3000,
            extendedTimeOut: 1500
          });
          this.checkLoading = false;
        }
      }, error => {
        if (error.status === 403) {
          this.toast.error('Đăng nhập thất bại, vui lòng nhập lại.', 'Thông báo');
          this.checkLoading = false;
        }
      }
    )
    ;
  }
}
