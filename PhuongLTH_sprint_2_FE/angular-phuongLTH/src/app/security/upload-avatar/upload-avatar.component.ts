import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AngularFireStorage, AngularFireStorageReference} from '@angular/fire/storage';

@Component({
  selector: 'app-upload-avatar',
  templateUrl: './upload-avatar.component.html',
  styleUrls: ['./upload-avatar.component.css']
})
export class UploadAvatarComponent implements OnInit {

  selectedFire: File | undefined;
  ref: AngularFireStorageReference | undefined;
  downloadURL: string | undefined;
  checkUpload = false;

  constructor(private afStorage: AngularFireStorage) {
  }


  @Output()
  giveURLtoCreate = new EventEmitter();

  ngOnInit(): void {
  }

  // khi upload file qua thẻ dưới dạng 1 hoặc nhiều file thì tệp đó thông qua sự kiên chhange
  // $event được kích hoạt.
  // và tất cả file upload lưu trữ ở trong $event.tager.files[]
  // @ts-ignore
  onFileChanged($event): void {
    this.selectedFire = $event.target.files[0];
  }

  onUpload(): void {
    this.checkUpload = true;
    const nameUrl = Math.random().toString(36).substring(2); // Tạo ra  1 tên riêng trên DB của fiirebase
    this.ref = this.afStorage.ref(nameUrl);
    this.ref.put(this.selectedFire).then(snapshot => {
      return snapshot.ref.getDownloadURL(); // trả về chuổi siêu văn bản trên FB
    }).then(downloadURL => { // chuyển link đến component khác nhau khi upload
      this.downloadURL = downloadURL;
      this.giveURLtoCreate.emit(this.downloadURL);
      this.checkUpload = false;
      return downloadURL;
    }).catch(error => {
    });
  }
}
