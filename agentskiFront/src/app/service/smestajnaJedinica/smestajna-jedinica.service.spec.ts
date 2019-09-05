import { TestBed } from '@angular/core/testing';

import { SmestajnaJedinicaService } from './smestajna-jedinica.service';

describe('SmestajnaJedinicaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SmestajnaJedinicaService = TestBed.get(SmestajnaJedinicaService);
    expect(service).toBeTruthy();
  });
});
