import { TestBed } from '@angular/core/testing';

import { KomentarService } from './komentar.service';

describe('KomentarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KomentarService = TestBed.get(KomentarService);
    expect(service).toBeTruthy();
  });
});
