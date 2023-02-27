import { TestBed } from '@angular/core/testing';

import { ADMINUSERGuard } from './admin-user.guard';

describe('ADMINUSERGuard', () => {
  let guard: ADMINUSERGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ADMINUSERGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
