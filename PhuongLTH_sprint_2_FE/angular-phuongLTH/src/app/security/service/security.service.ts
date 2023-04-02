import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment.prod';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JwtResponse} from '../../entity/account/JwtResponse';
import {SignInForm} from '../../entity/account/SignInForm';
import {Customer} from '../../entity/customer/customer';
import {Orders} from '../../dto/product/orders';
import {TokenDto} from '../../dto/token/TokenDto';

const header = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private API_SIGNIN = environment.API_LOCAL + '/signin';
  private API_SIGNIN_GOOGLE = environment.API_LOCAL + '/google';
  private API_SIGNIN_FACEBOOK = environment.API_LOCAL + '/facebook';
  private API_SIGNUP = environment.API_LOCAL + '/signup';
  private API_CHANGE_AVATAR = environment.API_LOCAL + '/change-avatar';
  private API_ORDER_INFO = environment.API_PRODUCT + '/order/create';
  private API_CHECK_VALID_EMAIL = environment.API_LOCAL + '/check-valid/';

  constructor(private httpClient: HttpClient) {
  }


  signIn(signInForm: SignInForm): Observable<any> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN, signInForm);
  }

  signUp(customer: Customer): Observable<Customer> {
    return this.httpClient.post<Customer>(this.API_SIGNUP, customer);
  }

  changeAvatar(info: any): Observable<JwtResponse> {
    return this.httpClient.put<JwtResponse>(this.API_CHANGE_AVATAR, info);
  }

  infoOrder(infoOrder: Orders): Observable<Orders> {
    return this.httpClient.post<Orders>(this.API_ORDER_INFO, infoOrder);
  }

  google(tokenDto: TokenDto): Observable<TokenDto> {
    return this.httpClient.post<TokenDto>(this.API_SIGNIN_GOOGLE, tokenDto);
  }

  facebook(tokenDto: TokenDto): Observable<JwtResponse> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN_FACEBOOK, tokenDto, header);
  }

  checkValid(value: string): Observable<any> {
    return this.httpClient.get(this.API_CHECK_VALID_EMAIL + value);
  }
}
