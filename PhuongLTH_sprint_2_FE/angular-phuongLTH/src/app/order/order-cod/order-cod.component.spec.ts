import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderCodComponent } from './order-cod.component';

describe('OrderCodComponent', () => {
  let component: OrderCodComponent;
  let fixture: ComponentFixture<OrderCodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderCodComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderCodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
