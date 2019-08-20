import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazKorisnikaComponent } from './prikaz-korisnika.component';

describe('PrikazKorisnikaComponent', () => {
  let component: PrikazKorisnikaComponent;
  let fixture: ComponentFixture<PrikazKorisnikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazKorisnikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazKorisnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
