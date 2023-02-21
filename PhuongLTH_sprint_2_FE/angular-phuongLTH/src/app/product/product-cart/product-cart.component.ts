import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {ProductView} from '../../dto/product/product-view';

@Component({
  selector: 'app-product-cart',
  templateUrl: './product-cart.component.html',
  styleUrls: ['./product-cart.component.css']
})
export class ProductCartComponent implements OnInit {


  constructor(
    private productService: ProductService
  ) {
  }

  ngOnInit(): void {
  }

}
