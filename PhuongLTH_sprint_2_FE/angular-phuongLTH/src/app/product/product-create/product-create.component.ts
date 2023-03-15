import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {formatDate} from '@angular/common';
import {AngularFireStorage, AngularFireStorageReference} from '@angular/fire/storage';
import {finalize} from 'rxjs/operators';
import {ProductService} from '../service/product.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Category} from '../../dto/product/category';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  form: any = {};
  productForm: FormGroup = new FormGroup({});
  selectedImage: any = null;
  ref: AngularFireStorageReference | undefined;
  downloadURL: string | undefined;
  checkUpload = false;
  categoryList: Category[] | undefined;

  constructor(
    private storage: AngularFireStorage,
    private productService: ProductService,
    private router: Router,
    private toast: ToastrService
  ) {
    this.productForm = new FormGroup({
      autoWhiteBalanceFunction: new FormControl('', [Validators.required]),
      nameProduct: new FormControl('', [Validators.required]),
      categoryProduct: new FormControl('', [Validators.required]),
      imageSensor: new FormControl('', [Validators.required]),
      infraredVision: new FormControl('', [Validators.required]),
      speedRecord: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      material: new FormControl('', [Validators.required]),
      memory: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
      resolution: new FormControl('', [Validators.required]),
      url: new FormControl('', [Validators.required]),
      price: new FormControl('', [Validators.required]),
    });
    this.productService.getAllCategory().subscribe(data => {
      this.categoryList = data;
    });
  }

  ngOnInit(): void {
  }


  createProduct(): void {
    this.checkUpload = true;
    const nameImg = this.getCurrentDateTime() + this.selectedImage.name;
    const fileRef = this.storage.ref(nameImg);
    this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((urlLink) => {
          this.productForm.patchValue({url: urlLink});
          this.productService.createProduct(this.productForm.value).subscribe(data => {
            this.toast.success('Thêm mới thành công.');
            this.productForm.reset();
            this.checkUpload = false;
          }, error => {
            if (error.status === 400) {
              this.checkUpload = false;
              this.toast.error('Thêm mới không thành công.');
            }
          });
        });
      })
    ).subscribe();
  }

  showPreview($event: Event): void {
    // @ts-ignore
    this.selectedImage = $event.target.files[0];
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }
}
