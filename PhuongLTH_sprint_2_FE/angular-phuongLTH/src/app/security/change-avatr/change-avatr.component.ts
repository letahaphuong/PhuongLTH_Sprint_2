import {Component, OnInit} from '@angular/core';
import {ChangeAvatar} from '../../entity/avatar/ChangeAvatar';
import {SecurityService} from '../service/security.service';
import {TokenService} from '../service/token.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-change-avatr',
  templateUrl: './change-avatr.component.html',
  styleUrls: ['./change-avatr.component.css']
})
export class ChangeAvatrComponent implements OnInit {

  form: any = {};
  changeAvatar: ChangeAvatar | undefined;
  error: any = {
    message: 'no'
  };
  success: any = {
    message: 'yes'
  };


  // tslint:disable-next-line:variable-name
  avatar_get: string | null;
  // tslint:disable-next-line:variable-name
  name_get: string | null;
  // tslint:disable-next-line:variable-name
  email_get: string | null;

  status = 'Xin hãy chọn ảnh và nhấn uplode';

  constructor(
    private securityService: SecurityService,
    private tokenService: TokenService,
    private router: Router,
    private toast: ToastrService
  ) {
    this.avatar_get = this.tokenService.getAvatar();
    this.name_get = this.tokenService.getName();
    this.email_get = this.tokenService.getEmail();
  }

  ngOnInit(): void {

  }

  onSubmit(): void {
    this.changeAvatar = new ChangeAvatar(
      this.form.avatar
    );
    this.changeAvatar = new ChangeAvatar(this.form.avatar);
    this.securityService.changeAvatar(this.changeAvatar).subscribe(data => {
      if (JSON.stringify(data) === JSON.stringify(this.error)) {
        this.status = 'Xin hãy upload avatar';
        this.toast.warning('Xin hãy upload hình ảnh');
      }
      if (JSON.stringify(data) === JSON.stringify(this.success)) {
        this.status = 'Thay đổi avatar thành công';
        this.toast.success('Thay đổi hình ảnh thành công.');
        this.tokenService.setAvatar(this.form.avatar);
        location.reload();
      }
    }, error1 => {
      this.toast.error('Thay đổi không thành công.');
    });
  }

  onUploadAvatar($event: any): void {
    this.form.avatar = $event;
  }

}
