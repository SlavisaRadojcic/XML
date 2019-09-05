import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazKomentaraComponent } from './prikaz-komentara.component';

describe('PrikazKomentaraComponent', () => {
  let component: PrikazKomentaraComponent;
  let fixture: ComponentFixture<PrikazKomentaraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazKomentaraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazKomentaraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
