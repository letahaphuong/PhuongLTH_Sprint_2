import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YooseeComponent } from './yoosee.component';

describe('YooseeComponent', () => {
  let component: YooseeComponent;
  let fixture: ComponentFixture<YooseeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YooseeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YooseeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
