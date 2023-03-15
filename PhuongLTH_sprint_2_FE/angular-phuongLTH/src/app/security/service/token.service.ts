import {Injectable} from '@angular/core';

const TOKEN_KEY = 'Token_key';
const NAME_KEY = 'Name_key';
const ROLE_KEY = 'Role_key';
const ID_KEY = 'Id_key';
const EMAIL_KEY = 'Email_key';
const AVATAR_KEY = 'Avatar_key';
const ANONY_KEY = 'Anony_key';
const CUSTOMER_ID_KEY = 'Customer_Id_key';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  public roles = [];

  constructor() {
  }

  public setIdCustomer(idCustomer: string): void {
    localStorage.removeItem(CUSTOMER_ID_KEY);
    localStorage.setItem(CUSTOMER_ID_KEY, idCustomer);
  }

  public getIdCustomer(): string | null {
    return localStorage.getItem(CUSTOMER_ID_KEY);
  }

  public setAnony(anony: string): void {
    localStorage.removeItem(ANONY_KEY);
    localStorage.setItem(ANONY_KEY, anony);
  }

  public getAnony(): string | null {
    return localStorage.getItem(ANONY_KEY);
  }

  public setAvatar(avatar: string): void {
    localStorage.removeItem(AVATAR_KEY);
    localStorage.setItem(AVATAR_KEY, avatar);
  }

  public getAvatar(): string | null {
    return localStorage.getItem(AVATAR_KEY);
  }

  public setEmail(email: string): void {
    localStorage.removeItem(EMAIL_KEY);
    localStorage.setItem(EMAIL_KEY, email);
  }

  public getEmail(): string | null {
    return localStorage.getItem(EMAIL_KEY);
  }

  public setId(id: string): void {
    localStorage.removeItem(ID_KEY);
    localStorage.setItem(ID_KEY, id);
  }

  public getId(): string | null {
    return localStorage.getItem(ID_KEY);
  }

  public setToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  public setName(name: string): void {
    localStorage.removeItem(NAME_KEY);
    localStorage.setItem(NAME_KEY, name);
  }

  public getName(): string | null {
    return localStorage.getItem(NAME_KEY);
  }


  // public setRole(roles: string[]): void {
  //   localStorage.removeItem(ROLE_KEY);
  //   localStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  // }
  //
  //
  // public getRole(): string[] {
  //   this.roles = [];
  //   if (this.getToken()) {
  //     // @ts-ignore
  //     JSON.parse(localStorage.getItem(ROLE_KEY)).forEach(role => {
  //       // @ts-ignore
  //       this.roles.push(role.authority);
  //     });
  //   }
  //   return this.roles;
  // }

  public rememberMe(roles: string[], name: string, token: string, avatar: string, email: string, id: string): void {
    this.setRoleSession(roles);
    this.setNameSession(name);
    this.setTokenSession(token);
    this.setAvatarSession(avatar);
    this.setEmailSession(email);
    this.setIdSession(id);
  }

  public getRoleSession(): string[] {
    this.roles = [];
    if (this.getToken()) {
      // @ts-ignore
      JSON.parse(sessionStorage.getItem(ROLE_KEY)).forEach(role => {
        // @ts-ignore
        this.roles.push(role.authority);
      });
    }
    return this.roles;
  }

  public setRoleSession(roles: string[]): void {
    sessionStorage.removeItem(ROLE_KEY);
    sessionStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }

  public setAnonySession(anony: string): void {
    sessionStorage.removeItem(ANONY_KEY);
    sessionStorage.setItem(ANONY_KEY, anony);
  }

  public setIdCustomerSession(idCustomer: string): void {
    sessionStorage.removeItem(CUSTOMER_ID_KEY);
    sessionStorage.setItem(CUSTOMER_ID_KEY, idCustomer);
  }

  public getIdCustomerSession(): string | null {
    return sessionStorage.getItem(CUSTOMER_ID_KEY);
  }

  public getAnonySession(): string | null {
    return sessionStorage.getItem(ANONY_KEY);
  }

  public setNameSession(name: string): void {
    sessionStorage.removeItem(NAME_KEY);
    sessionStorage.setItem(NAME_KEY, name);
  }

  public getNameSession(): string | null {
    return sessionStorage.getItem(NAME_KEY);
  }

  public setAvatarSession(avatar: string): void {
    sessionStorage.removeItem(AVATAR_KEY);
    sessionStorage.setItem(AVATAR_KEY, avatar);
  }

  public getAvatarSession(): string | null {
    return sessionStorage.getItem(AVATAR_KEY);
  }

  public setEmailSession(email: string): void {
    sessionStorage.removeItem(EMAIL_KEY);
    sessionStorage.setItem(EMAIL_KEY, email);
  }

  public getEmailSession(): string | null {
    return sessionStorage.getItem(EMAIL_KEY);
  }

  public setIdSession(id: string): void {
    sessionStorage.removeItem(ID_KEY);
    sessionStorage.setItem(ID_KEY, id);
  }

  public getIdSession(): string | null {
    return sessionStorage.getItem(ID_KEY);
  }

  public setTokenSession(token: string): void {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getTokenSession(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  logOut(): void {
    sessionStorage.clear();
  }
}
