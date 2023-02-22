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
      autoWhiteBalanceFunction: new FormControl(),
      nameProduct: new FormControl(),
      categoryProduct: new FormControl(),
      imageSensor: new FormControl(),
      infraredVision: new FormControl(),
      speedRecord: new FormControl(),
      description: new FormControl(),
      material: new FormControl(),
      memory: new FormControl(),
      quantity: new FormControl(),
      resolution: new FormControl(),
      url: new FormControl(),
      price: new FormControl(),
    });
    this.productService.getAllCategory().subscribe(data => {
      console.log(data);
      this.categoryList = data;
    });
  }

  ngOnInit(): void {
  }


  createProduct(): void {
    console.log(this.productForm);
    this.checkUpload = true;
    const nameImg = this.getCurrentDateTime() + this.selectedImage.name;
    const fileRef = this.storage.ref(nameImg);
    this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((urlLink) => {
          this.productForm.patchValue({url: urlLink});
          this.productService.createProduct(this.productForm.value).subscribe(data => {
            this.toast.success('Thêm mới thành công');
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
