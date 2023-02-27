import {Injectable} from '@angular/core';
// @ts-ignore
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class EncryInforService {
  encPassword?: '123123';
  decPassword?: '123123';

  constructor() {
  }

  conversionEncryptInput(conversion: string): any {
   return CryptoJS.AES.encrypt(conversion.trim(), this.encPassword?.trim()).toString();
  }

  conversionDecryptOutput(conversion: string): any {
    return  CryptoJS.AES.decrypt(conversion.trim(), this.decPassword?.trim()).toString(CryptoJS.enc.Utf8);
  }
}
