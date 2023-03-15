import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {TokenService} from '../../security/service/token.service';
// @ts-ignore
import * as CryptoJS from 'crypto-js';
import {MessageService} from '../../product/service/message.service';
import {SocialAuthService, SocialUser} from 'angularx-social-login';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  userLogged: SocialUser | undefined;
  isLogged: boolean | undefined;

  checkLogin = false;
  name: string | null | undefined;
  roles: string[] = [];
  avatar: string | null | undefined;
  idAccount: any;
  anony: string | null | undefined;
  decPassword = '123123';
  number = 0;
  count = 0;

  constructor(private toast: ToastrService,
              private router: Router,
              private tokenService: TokenService,
              private messageService: MessageService,
              private authService: SocialAuthService
  ) {
    this.getNumber();
    this.idAccount = this.tokenService.getId();
    this.authService.authState.subscribe(
      data => {
        this.userLogged = data;
        this.tokenService.setAvatar(this.userLogged.photoUrl);
        this.tokenService.setName(this.userLogged.name);
        this.isLogged = (this.userLogged != null && this.tokenService.getToken() != null);
      }
    );
  }

  getNumber(): void {
    this.messageService.getMessageNumber().subscribe(data => {
      console.log('heder', data);
      this.number = data;
    });
  }

  ngOnInit(): void {
    this.getNumber();
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      // @ts-ignore
      this.name = this.tokenService.getName();
      // this.name = CryptoJS.AES.decrypt(this.tokenService.getName().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8);
      // this.roles = this.tokenService.getRole();
      this.avatar = this.tokenService.getAvatar();
      // @ts-ignore
      this.anony = this.tokenService.getAnony();
    } else if (this.tokenService.getTokenSession()) {
      this.checkLogin = true;
      this.name = this.tokenService.getNameSession();
      this.roles = this.tokenService.getRoleSession();
      this.avatar = this.tokenService.getAvatarSession();
    }
  }

  logOut(): void {
    this.tokenService.logOut();
    window.localStorage.clear();
    window.sessionStorage.clear();
    this.router.navigateByUrl('/').then(() => {
      location.reload();
    });
    this.toast.info('Đăng xuất thành công.', ' Thông báo', {
      timeOut: 3000,
      extendedTimeOut: 1500
    });
  }

  seach(search: string): void {
    this.messageService.setMessageSearch(search);
  }

  noti(): void {
    this.toast.info('Chức năng đang trong qua trình phát triển.');
  }

  superAdminLogin(): void {
    this.count += 1;
    console.log(this.count);
    if (this.count === 5) {
      this.router.navigate(['/security/login-sp-admin']);
      this.count = 0;
    }
  }

  superChange(): void {
    this.count += 1;
    console.log(this.count);
    if (this.count === 5) {
      this.router.navigate(['/security/management-page']);
      this.count = 0;
    }
  }
}
