import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import {NgxPaginationModule} from 'ngx-pagination';
import { NgbModule, NgbModalRef, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { HInterceptorService } from './h-interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { AuthService } from './service/user/auth.service';
import { LoginComponent } from './component/login/login.component';
import { DodavanjeSmestajneJediniceComponent } from './component/dodavanje-smestajne-jedinice/dodavanje-smestajne-jedinice.component';
import { SmestajnaJedinicaService } from './service/smestajnaJedinica/smestajna-jedinica.service';
import { PrikazSmestajaComponent } from './component/prikaz-smestaja/prikaz-smestaja.component';
import { PrikazSmestajneJediniceComponent } from './component/prikaz-smestajne-jedinice/prikaz-smestajne-jedinice.component';


const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'smestajne-jedinice', component: PrikazSmestajaComponent }, //kada se generise smestajan jedinica dodati ovde
  { path: 'prikaz-smestajne-jedinice', component: PrikazSmestajneJediniceComponent },
  { path: 'dodavanje-smestajne-jedinice', component: DodavanjeSmestajneJediniceComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }, 
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    LoginComponent,
    DodavanjeSmestajneJediniceComponent,
    PrikazSmestajaComponent,
    PrikazSmestajneJediniceComponent,
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
      { enableTracing: true, useHash: true } // <-- debugging purposes only
    )
  ],
  providers: [ //registrujem servise obaveznoo!!!!!!
    NgbActiveModal,
    DatePipe,
    AuthService,
    SmestajnaJedinicaService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
