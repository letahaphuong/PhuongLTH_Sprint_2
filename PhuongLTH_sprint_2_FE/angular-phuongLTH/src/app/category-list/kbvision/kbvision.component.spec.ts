import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KbvisionComponent } from './kbvision.component';

describe('KbvisionComponent', () => {
  let component: KbvisionComponent;
  let fixture: ComponentFixture<KbvisionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KbvisionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KbvisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
