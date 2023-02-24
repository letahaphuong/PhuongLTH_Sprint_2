import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../product/service/product.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  ordersForm: FormGroup = new FormGroup({});

  constructor(private productService: ProductService) {
    this.ordersForm = new FormGroup({
      idOrder: new FormControl(),
      codeOrder: new FormControl(),
      paymentStatus: new FormControl(),
      shippingAddress: new FormControl(),
      orderPhoneNumber: new FormControl(),
      customer: new FormControl()
    });
  }

  ngOnInit(): void {
  }

}
