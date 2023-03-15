import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenService} from '../../security/service/token.service';
import {AngularFireMessaging} from '@angular/fire/messaging';

@Injectable()
export class FireInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService,
    private angularFireMessaging: AngularFireMessaging
  ) {
  }

  intercept(request: HttpRequest<unknown>,
            next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.tokenService.getToken() != null) {
      const token = 'AAAAzX4wIXc:APA91bE1zxWe4kf5sk2Dqjc3vOvaJ19ee2JbMuhK-8nlEPX899Y7_l54GMwlABkW4jc-C0-FIz5bokJ2PdK_y0piWv6yrFuG_g2OkbdHHrK6EpUlm2ujMaLCUYlbPSTIerbMdov290iN';
      // @ts-ignore
      const headers = new HttpHeaders().set('Authorization', 'key=' + token);
      const AuthRequest = request.clone({headers});
      return next.handle(AuthRequest);
    } else {
      return next.handle(request);
    }

  }
}
