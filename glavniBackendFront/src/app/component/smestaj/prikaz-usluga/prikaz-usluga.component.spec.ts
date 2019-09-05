import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrikazUslugaComponent } from './prikaz-usluga.component';

describe('PrikazUslugaComponent', () => {
  let component: PrikazUslugaComponent;
  let fixture: ComponentFixture<PrikazUslugaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrikazUslugaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrikazUslugaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
