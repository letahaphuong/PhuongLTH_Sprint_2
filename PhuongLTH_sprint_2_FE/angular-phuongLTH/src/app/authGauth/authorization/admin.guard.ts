import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {TokenService} from '../../security/service/token.service';
import {ToastrService} from 'ngx-toastr';
// @ts-ignore
import * as CryptoJS from 'crypto-js';
@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {
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
      if (this.tokenService.getAnony() === JSON.stringify(2)) {
        console.log(this.tokenService.getAnony());
        return true;
      } else {

        console.log(this.tokenService.getAnony());
        this.toast.warning('Vui lòng đăng nhập để sử dụng chức năng này.');
        this.router.navigateByUrl('');
        return false;
      }
    } else {
      return false;
    }
  }
}
