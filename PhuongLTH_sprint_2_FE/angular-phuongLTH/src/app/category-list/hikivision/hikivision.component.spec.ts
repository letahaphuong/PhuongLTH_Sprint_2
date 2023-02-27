import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HikivisionComponent } from './hikivision.component';

describe('HikivisionComponent', () => {
  let component: HikivisionComponent;
  let fixture: ComponentFixture<HikivisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HikivisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HikivisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
