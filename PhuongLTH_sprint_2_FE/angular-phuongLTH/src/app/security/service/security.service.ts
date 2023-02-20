import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtResponse} from '../../entity/account/JwtResponse';
import {SignInForm} from '../../entity/account/SignInForm';
import {Customer} from '../../entity/customer/customer';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private API_SIGNIN = environment.API_LOCAL + '/signin';
  private API_SIGNUP = environment.API_LOCAL + '/signup';
  private API_CHANGE_AVATAR = environment.API_LOCAL + '/change-avatar';

  constructor(private httpClient: HttpClient) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    'Access-Control-Allow-Origin': 'http://localhost:4200',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
  };

  signIn(signInForm: SignInForm): Observable<any> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN, signInForm, this.httpOptions);
  }

  signUp(customer: Customer): Observable<Customer> {
    return this.httpClient.post<Customer>(this.API_SIGNUP, customer);
  }

  changeAvatar(info: any): Observable<JwtResponse> {
    console.log('avaterrrrr --->', info);
    return this.httpClient.put<JwtResponse>(this.API_CHANGE_AVATAR, info);
  }
}
