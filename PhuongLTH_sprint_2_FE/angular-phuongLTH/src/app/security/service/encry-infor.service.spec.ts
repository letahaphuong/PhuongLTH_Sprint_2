import { TestBed } from '@angular/core/testing';

import { EncryInforService } from './encry-infor.service';

describe('EncryInforService', () => {
  let service: EncryInforService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncryInforService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
