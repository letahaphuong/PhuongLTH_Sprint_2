import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FireBaseService {
  API_FIRE_BASE = 'https://fcm.googleapis.com/fcm/send';

  constructor(private http: HttpClient) {
  }

  send(form: any): Observable<any> {
    return this.http.post(this.API_FIRE_BASE, form);
  }
}
