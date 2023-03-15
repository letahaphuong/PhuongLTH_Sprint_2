import {Component, OnInit} from '@angular/core';
import {TokenService} from '../../security/service/token.service';
import {FormControl, FormGroup} from '@angular/forms';
import {AngularFireStorage} from '@angular/fire/storage';
import {ProductService} from '../service/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Category} from '../../dto/product/category';
import {formatDate} from '@angular/common';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  checkUpload = false;
  productEditForm: FormGroup = new FormGroup({});
  categoryList: Category[] | undefined;
  url!: string;
  selectedImage: any = null;
  checkInputUpload = false;

  compareWith(o1: Category, o2: Category): boolean {
    return o1 && o2 ? o1.idCategory === o2.idCategory : o1 === o2;
  }

  constructor(
    private tokenService: TokenService,
    private storage: AngularFireStorage,
    private productService: ProductService,
    private router: Router,
    private toast: ToastrService,
    private activatedRoute: ActivatedRoute
  ) {
    this.productEditForm = new FormGroup({
      autoWhiteBalanceFunction: new FormControl(),
      idProduct: new FormControl(),
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
    this.activatedRoute.paramMap.subscribe(data => {
      const idProduct = data.get('idProduct');
      if (idProduct != null) {
        this.getProductById(Number(idProduct));
      }
    });
    this.productService.getAllCategory().subscribe(data => {
      this.categoryList = data;
    });
  }

  getProductById(id: number): void {
    this.productService.getProductById(id).subscribe(data => {
      this.productEditForm.patchValue(data);
      this.url = data.url;
    });
  }

  ngOnInit(): void {
  }

  editProduct(): void {
    if (this.checkInputUpload && this.selectedImage != null) {
      this.checkUpload = true;
      const nameImg = this.getCurrentDateTime() + this.selectedImage.name;
      const fileRef = this.storage.ref(nameImg);
      this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe((urlLink) => {
            this.productEditForm.patchValue({url: urlLink});
            this.productService.editProduct(this.productEditForm.value).subscribe(data => {
              this.checkUpload = false;
              this.toast.success('Sửa thông tin thành công.');
            }, error => {
              if (error.status === 400) {
                this.checkUpload = false;
                this.toast.error('Sửa không thành công.');
              }
            });
          });
        })
      ).subscribe();
    } else {
      this.productService.editProduct(this.productEditForm.value).subscribe(data => {
        this.toast.success('Sửa thông tin thành công.');
        this.router.navigateByUrl('/product');
      }, error => {
        this.toast.error('Sửa không thành công.');
      });
    }
  }

  showPreview($event: Event): void {
    this.checkInputUpload = true;
    // @ts-ignore
    this.selectedImage = $event.target.files[0];
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }
}
