import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazTipaSmestajaComponent } from './prikaz-tipa-smestaja.component';

describe('PrikazTipaSmestajaComponent', () => {
  let component: PrikazTipaSmestajaComponent;
  let fixture: ComponentFixture<PrikazTipaSmestajaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazTipaSmestajaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazTipaSmestajaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
