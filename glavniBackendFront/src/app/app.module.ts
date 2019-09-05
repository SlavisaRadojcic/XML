import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DatePipe } from '@angular/common';
import { LoginComponent } from './component/korisnik/login/login.component';
import { RegistracijaComponent } from './component/korisnik/registracija/registracija.component';
import { AuthService } from './service/korisnik/auth.service';
import { KorisnikService } from './service/korisnik/korisnik.service';
import { PrikazKorisnikaComponent } from './component/korisnik/prikaz-korisnika/prikaz-korisnika.component';
import { PrikazSmestajaComponent } from './component/smestaj/prikaz-smestaja/prikaz-smestaja.component';
import { SmestajService } from './service/smestaj/smestaj.service';
import { PrikazRezervacijaComponent } from './component/smestaj/prikaz-rezervacija/prikaz-rezervacija.component';
import { PrikazUslugaComponent } from './component/smestaj/prikaz-usluga/prikaz-usluga.component';
import { PrikazTipaSmestajaComponent } from './component/smestaj/prikaz-tipa-smestaja/prikaz-tipa-smestaja.component';
import { OceniRezervacijuComponent } from './component/korisnik/oceni-rezervaciju/oceni-rezervaciju.component';
import { MeniBarComponent } from './component/meni-bar/meni-bar.component';
import { PrikazKomentaraComponent } from './component/korisnik/prikaz-komentara/prikaz-komentara.component';
import { KomentarService } from './service/smestaj/komentar.service';



const appRoutes: Routes = [

  { path: 'login', component: LoginComponent },  
  { path: 'registracija', component: RegistracijaComponent },
  { path: 'registracija/:id', component: RegistracijaComponent },
  { path: 'smestajneJedinice', component: PrikazSmestajaComponent },
  { path: 'myReservation', component: PrikazRezervacijaComponent },
  { path: 'korisnici', component: PrikazKorisnikaComponent },
  { path: 'usluge', component: PrikazUslugaComponent },
  { path: 'oceni', component: OceniRezervacijuComponent },
  { path: 'tipSmestaja', component: PrikazTipaSmestajaComponent },
  { path: 'komentari', component: PrikazKomentaraComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    RegistracijaComponent,
    PrikazSmestajaComponent,
    PrikazKorisnikaComponent,
    PrikazRezervacijaComponent,
    PrikazUslugaComponent,
    PrikazTipaSmestajaComponent,
    OceniRezervacijuComponent,
    MeniBarComponent,
    PrikazKomentaraComponent,
  ],
  imports: [
    BrowserModule,
    AngularFontAwesomeModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    NgxPaginationModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true, useHash: true } 
    )
  ],
  providers: [ 
    NgbActiveModal,
    AuthService,
    DatePipe,
    KorisnikService,
    SmestajService,
    KomentarService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
