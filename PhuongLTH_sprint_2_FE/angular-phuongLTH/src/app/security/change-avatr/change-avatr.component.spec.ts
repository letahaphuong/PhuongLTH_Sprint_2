import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeAvatrComponent } from './change-avatr.component';

describe('ChangeAvatrComponent', () => {
  let component: ChangeAvatrComponent;
  let fixture: ComponentFixture<ChangeAvatrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeAvatrComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeAvatrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
