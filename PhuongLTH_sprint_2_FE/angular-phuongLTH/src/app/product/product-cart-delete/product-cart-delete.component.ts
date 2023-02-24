import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CartView} from '../../dto/product/cart-view';
import {ProductService} from '../service/product.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-product-cart-delete',
  templateUrl: './product-cart-delete.component.html',
  styleUrls: ['./product-cart-delete.component.css']
})
export class ProductCartDeleteComponent implements OnInit {
  @Input() cartView: CartView | undefined;
  @Output() eventCartDelete = new EventEmitter();
  constructor(private productService: ProductService,
              private toast: ToastrService) { }

  ngOnInit(): void {
  }

  delete(): void {
    const id = this.cartView?.idProductOrder;
    if (id != null) {
      this.productService.deleteCart(id).subscribe(data => {
        this.eventCartDelete.emit();
        this.toast.success('Xoá thành công');
        location.reload();
      }, error => {
        this.toast.error('Xoá không thành công.');
      });
    }
  }
}
