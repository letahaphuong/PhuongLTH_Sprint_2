import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../service/security.service';
import {Router} from '@angular/router';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators
} from '@angular/forms';
import {TokenService} from '../service/token.service';
import {ToastrService} from 'ngx-toastr';
import {Title} from '@angular/platform-browser';
import {differenceInYears} from 'date-fns';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  createCustomer: FormGroup = new FormGroup({});
  flag = false;
  valueCheck = '';
  error: any = {
    message: 'Email đã tồn tại'
  };
  success: any = {
    message: 'ok'
  };

  constructor(
    private securityService: SecurityService,
    private router: Router,
    private formBuilder: FormBuilder,
    private tokenService: TokenService,
    private toast: ToastrService,
    private title: Title
  ) {
    this.title.setTitle('Trang đăng ký');
    this.createCustomer = new FormGroup({
      idCustomer: new FormControl(),
      name: new FormControl('', [Validators.required, Validators.pattern(
        '[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'), Validators.minLength(3), Validators.maxLength(100)]),
      email: new FormControl('', [Validators.required, Validators.pattern('^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$'),
        Validators.minLength(5), Validators.maxLength(100)]),
      address: new FormControl('', [Validators.required
        , Validators.minLength(5), Validators.maxLength(100)]),
      idCard: new FormControl('', [Validators.required, Validators.pattern('^\\d{12}$')]),
      gender: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('', [Validators.required, this.validateOfBirth]),
      phone: new FormControl('', [Validators.required, Validators.pattern('(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})')]),
      encryptPassword: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    });
  }

  ngOnInit(): void {
  }

  create(): void {
    const customer = this.createCustomer.value;
    this.securityService.signUp(customer).subscribe(data => {
      this.toast.success('Thêm mới thành công.');
      this.router.navigateByUrl('/security/login');
    }, error => {
      if (error.status === 409) {
        this.toast.error('Thêm mới thất bại, em mail đã tồn tại.');
      }
      this.toast.error('Thêm mới không thành công.');
    });
  }

  validateOfBirth(c: AbstractControl): any {
    const date = new Date(c.value);
    const age = differenceInYears(new Date(), date);
    return (age <= 18) ? {greaterThan18: true} : null;
  }

  checkValid(value: string): void {
    console.log(value);
    if (value !== '') {
      this.securityService.checkValid(value).subscribe(data => {
        if (JSON.stringify(data.message) !== 'ok') {
          this.flag = true;
          this.valueCheck = data.message;
        }
      }, error => {

      });
    } else {
      this.flag = false;
      this.valueCheck = '';
    }
  }
}
