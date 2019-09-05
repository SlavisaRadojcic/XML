import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazSmestajneJediniceComponent } from './prikaz-smestajne-jedinice.component';

describe('PrikazSmestajneJediniceComponent', () => {
  let component: PrikazSmestajneJediniceComponent;
  let fixture: ComponentFixture<PrikazSmestajneJediniceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazSmestajneJediniceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazSmestajneJediniceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
