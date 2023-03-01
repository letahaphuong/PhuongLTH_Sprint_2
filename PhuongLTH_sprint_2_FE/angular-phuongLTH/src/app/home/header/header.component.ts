import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {TokenService} from '../../security/service/token.service';
// @ts-ignore
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false;
  name: string | null = '';
  roles: string[] = [];
  avatar: string | null | undefined ;
  idAccount: any;
  anony: string | null | undefined;
  encPassword = '123123';
  decPassword = '123123';

  constructor(private toast: ToastrService,
              private router: Router,
              private tokenService: TokenService) {
    this.idAccount = this.tokenService.getId();
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      // @ts-ignore
      this.name = CryptoJS.AES.decrypt(this.tokenService.getName().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8);
      // this.roles = this.tokenService.getRole();
      this.avatar = this.tokenService.getAvatar();
      // @ts-ignore
      this.anony = CryptoJS.AES.decrypt(this.tokenService.getAnony().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8);
    } else if (this.tokenService.getTokenSession()) {
      this.checkLogin = true;
      this.name = this.tokenService.getNameSession();
      this.roles = this.tokenService.getRoleSession();
      this.avatar = this.tokenService.getAvatarSession();
    }
  }

  logOut(): void {
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

  feature(): void {
    this.toast.info('Hiện tại chức năng đang update.');
  }
}
