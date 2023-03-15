import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MessagingService} from '../../service/messaging.service';
import {FireBaseService} from '../../service/fire-base.service';
import {AngularFireMessaging} from '@angular/fire/messaging';

@Component({
  selector: 'app-management-page',
  templateUrl: './management-page.component.html',
  styleUrls: ['./management-page.component.css']
})
export class ManagementPageComponent implements OnInit {

  constructor(
    private messagingService: MessagingService,
    private fireBaseService: FireBaseService,
    private angularFireMessaging: AngularFireMessaging
  ) {
  }

  token: string | null = '';

  ngOnInit(): void {
    this.messagingService.requestPermission();
    this.messagingService.receiveMessage();
    this.angularFireMessaging.requestToken.subscribe(data => {
      this.token = data;
    });
  }

  notify(tit: string, message: string): void {
    const body = {
      to: this.token,
      notification: {
        title: tit,
        body: message
      }
    };
    console.log(body);

    this.fireBaseService.send(body).subscribe(data => {
      console.log(data);
    });
  }
}
