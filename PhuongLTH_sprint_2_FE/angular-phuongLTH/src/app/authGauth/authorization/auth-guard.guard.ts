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
export class AuthGuardGuard implements CanActivate {
  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toast: ToastrService
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const expectedRole = route.data.expctedRole;
    const token = this.tokenService.getToken();
    if (token) {
      return true;
    } else {
      this.toast.warning('Vui lòng đăng nhập để sử dụng chức năng.');
      this.router.navigateByUrl('/security/login');
      return false;
    }
  }

}
