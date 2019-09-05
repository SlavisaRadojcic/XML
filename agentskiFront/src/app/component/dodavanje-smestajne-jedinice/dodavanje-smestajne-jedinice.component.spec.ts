import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DodavanjeSmestajneJediniceComponent } from './dodavanje-smestajne-jedinice.component';

describe('DodavanjeSmestajneJediniceComponent', () => {
  let component: DodavanjeSmestajneJediniceComponent;
  let fixture: ComponentFixture<DodavanjeSmestajneJediniceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DodavanjeSmestajneJediniceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DodavanjeSmestajneJediniceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
