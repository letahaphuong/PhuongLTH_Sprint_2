import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import {RatingUnit} from '../../entity/rating/rating-unit';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  @Input()
  max = 10;
  @Input()
  ratingValue = 5;
  @Input()
  showRatingValue = true;

  @Output()
  rateChange = new EventEmitter<number>();

  ratingUnits: Array<RatingUnit> = [];

  constructor() {
  }

  // ngOnChanges(changes: SimpleChanges) {
  //   if ('max' in changes) {
  //     let max = changes.max.currentValue;
  //     max = typeof max === 'undefined' ? 5 : max;
  //     this.max = max;
  //     this.calculate(max, this.ratingValue);
  //   }
  // }

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

  select(index: number): void {
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
