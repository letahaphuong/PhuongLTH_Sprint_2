import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductView} from '../../dto/product/product-view';
import {ProductService} from "../service/product.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-product-delete',
  templateUrl: './product-delete.component.html',
  styleUrls: ['./product-delete.component.css']
})
export class ProductDeleteComponent implements OnInit {
  @Input()
  productView: ProductView | undefined;
  @Output()
  eventDelete = new EventEmitter();

  constructor(
    private productService: ProductService,
    private toast: ToastrService
  ) {
  }

  ngOnInit(): void {
  }

  delete(): void {
    const id = this.productView?.idProduct;
    if (id != null) {
      this.productService.deleteProduct(id).subscribe(data => {
        this.toast.success('Xoá thành công');
        this.eventDelete.emit();
      }, error => {
        this.toast.error('Xoá không thành công.');
      });
    }
  }
}
