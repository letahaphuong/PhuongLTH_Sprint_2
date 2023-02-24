import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCartDeleteComponent } from './product-cart-delete.component';

describe('ProductCartDeleteComponent', () => {
  let component: ProductCartDeleteComponent;
  let fixture: ComponentFixture<ProductCartDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductCartDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCartDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
