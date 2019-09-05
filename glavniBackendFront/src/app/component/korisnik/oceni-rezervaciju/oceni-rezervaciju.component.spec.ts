import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OceniRezervacijuComponent } from './oceni-rezervaciju.component';

describe('OceniRezervacijuComponent', () => {
  let component: OceniRezervacijuComponent;
  let fixture: ComponentFixture<OceniRezervacijuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OceniRezervacijuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OceniRezervacijuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
