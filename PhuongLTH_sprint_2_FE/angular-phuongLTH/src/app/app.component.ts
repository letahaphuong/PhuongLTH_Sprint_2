import {Component} from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {MessagingService} from "./service/messaging.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-phuongLTH';
  message: BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor(private messagingService: MessagingService) {
  }


  ngOnInit(): void {
    this.messagingService.requestPermission();
    this.messagingService.receiveMessage();
    this.message = this.messagingService.currentMessage;
  }

  scroll($event: any): void {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }
}
