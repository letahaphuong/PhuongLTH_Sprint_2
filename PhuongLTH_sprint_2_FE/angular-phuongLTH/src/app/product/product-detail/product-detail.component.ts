import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {RatingUnit} from '../../entity/rating/rating-unit';
import {ProductService} from '../service/product.service';
import {ProductView} from '../../dto/product/product-view';
import {Title} from '@angular/platform-browser';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(
    private productService: ProductService,
    private title: Title,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toast: ToastrService
  ) {
    this.activatedRoute.paramMap.subscribe(param => {
      console.log(param.get('idProduct'));
      const idProduct = param.get('idProduct');
      if (idProduct !== null) {
        this.idProduct = Number(idProduct);
        this.productService.getProductById(this.idProduct).subscribe(data => {
          this.productDetail = data;
          console.log(data);
        }, error => {

        }, () => {

        });
      }
    });
  }

  productDetail: ProductView | undefined;
  idProduct = 0;

  @Input()
  max = 10;
  @Input()
  ratingValue = 5;
  @Input()
  showRatingValue = true;

  @Output()
  rateChange = new EventEmitter<number>();

  ratingUnits: Array<RatingUnit> = [];
  quantityShow = 1;
  i = 1;


  calculate(max: number, ratingValue: number): void {
    this.ratingUnits = Array.from({length: max},
      (_, index) => ({
        value: index + 1,
        active: index < ratingValue
      }));
  }

  ngOnInit(): void {
    this.calculate(this.max, this.ratingValue);
  }

  minus = () => {
    if (this.i !== 1) {
      this.i--;
      this.quantityShow = this.i;
    }
  }

  plus = () => {
    if (this.i !== 100) {
      this.i++;
      this.quantityShow = this.i;
    }
  }

  select(index: number): void {
    this.toast.success('Cảm ơn bạn đã đánh giá.');
    this.ratingValue = index + 1;
    this.ratingUnits.forEach((item, idx) => item.active = idx < this.ratingValue);
    this.rateChange.emit(this.ratingValue);
  }

  enter(index: number): void {
    this.ratingUnits.forEach((item, idx) => item.active = idx <= index);
  }

  reset(): void {
    this.ratingUnits.forEach((item, idx) => item.active = idx < this.ratingValue);
  }

}
