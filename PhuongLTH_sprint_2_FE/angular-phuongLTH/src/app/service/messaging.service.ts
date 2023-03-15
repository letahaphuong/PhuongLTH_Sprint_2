import {Injectable} from '@angular/core';
import {AngularFireMessaging} from '@angular/fire/messaging';
import {BehaviorSubject} from 'rxjs';

@Injectable()
export class MessagingService {
  currentMessage = new BehaviorSubject(null);

  constructor(private angularFireMessaging: AngularFireMessaging) {
    this.angularFireMessaging.messages.subscribe(
      (data: any) => {
        data.onMessage = data.onMessage.bind(data);
        data.onTokenRefresh = data.onTokenRefresh.bind(data);
      }
    );
  }

  requestPermission(): void {
    this.angularFireMessaging.requestToken.subscribe(
      (token) => {
        console.log(token);
      },
      (err) => {
        console.error('Unable to get permission to notify.', err);
      }
    );
  }

  receiveMessage(): void {
    this.angularFireMessaging.messages.subscribe(
      payload => {
        console.log('new message received. ', payload);
        // @ts-ignore
        this.currentMessage.next(payload);
        this.showCustomNotification(payload);
      });
  }

  showCustomNotification(payload: any): void {
    // tslint:disable-next-line:variable-name
    const notify_data = payload.notification;
    console.log(notify_data);
    const title = notify_data.title;
    const options = {
      body: notify_data.body,
      icon: './assets/images/1.jpg',
      badge: './assets/images/2.jpg',
      image: './assets/images/1.jpg',
    };
    console.log('tin nhắn mới', notify_data);
    const notify: Notification = new Notification(title, options);

    notify.onclick = event => {
      event.preventDefault();
      window.location.href = 'http://www.google.com';
    };
  }
}
