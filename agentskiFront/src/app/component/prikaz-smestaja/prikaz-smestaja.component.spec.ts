import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSmestajaComponent } from './prikaz-smestaja.component';

describe('PrikazSmestajaComponent', () => {
  let component: PrikazSmestajaComponent;
  let fixture: ComponentFixture<PrikazSmestajaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSmestajaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSmestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
