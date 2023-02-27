import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ToastrService} from 'ngx-toastr';
import {TokenService} from '../../security/service/token.service';

@Injectable({
  providedIn: 'root'
})
export class ADMINUSERGuard implements CanActivate {
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
      if (JSON.stringify(this.tokenService.getRole()) === JSON.stringify(['ADMIN'])) {
        return true;
      } else if (JSON.stringify(this.tokenService.getRole()) === JSON.stringify(['USER'])) {
        return true;
      } else {
        this.toast.warning('Bạn không đủ quyền, vui lòng đăng nhập để tiếp tục.', 'Thông báo');
        this.router.navigateByUrl('');
        return false;
      }
    } else {
      this.router.navigateByUrl('');
      return false;
    }
  }

}
