import { TestBed } from '@angular/core/testing';

import { SmestajService } from './smestaj.service';

describe('SmestajService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SmestajService = TestBed.get(SmestajService);
    expect(service).toBeTruthy();
  });
});
