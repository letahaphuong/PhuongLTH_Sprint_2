import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../security/service/token.service';
// @ts-ignore
import * as CryptoJS from 'crypto-js';
@Injectable({
  providedIn: 'root'
})
export class ADMINUSERGuard implements CanActivate {
  decPassword = '123123';
  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toast: ToastrService
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.tokenService.getToken()) {
      // @ts-ignore
      if (JSON.stringify(CryptoJS.AES.decrypt(this.tokenService.getAnony().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8)) === JSON.stringify('2')) {
        return true;
      } else { // @ts-ignore
        if (JSON.stringify(CryptoJS.AES.decrypt(this.tokenService.getAnony().toString(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8)) === JSON.stringify('1')) {
                return true;
              } else {
                this.toast.warning('Bạn không đủ quyền, vui lòng đăng nhập để tiếp tục.', 'Thông báo');
                this.router.navigateByUrl('');
                return false;
              }
      }
    } else {
      this.router.navigateByUrl('');
      return false;
    }
  }

}
