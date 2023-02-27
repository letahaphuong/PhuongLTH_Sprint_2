import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VantechComponent } from './vantech.component';

describe('VantechComponent', () => {
  let component: VantechComponent;
  let fixture: ComponentFixture<VantechComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VantechComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VantechComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
