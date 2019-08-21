import { Component, OnInit } from '@angular/core';
import { KriterijumiPretrage } from 'app/model/smestajnaJedinica/kriterijumiPretrage';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { AuthService } from 'app/service/korisnik/auth.service';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { Adresa } from 'app/model/smestajnaJedinica/adresa';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { SmestajService } from 'app/service/smestaj/smestaj.service';

@Component({
  selector: 'app-prikaz-smestaja',
  templateUrl: './prikaz-smestaja.component.html',
  styleUrls: ['./prikaz-smestaja.component.css']
})
export class PrikazSmestajaComponent implements OnInit {

  p: number;
  accommodationSearch: KriterijumiPretrage = new KriterijumiPretrage();
  beginDate: NgbDate;
  endDate: NgbDate;
  accommodationToShow : SmestajnaJedinica[] = [];

  loggedUser: Korisnik = new Korisnik();

  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  constructor(private route: ActivatedRoute, private router: Router, public datepipe: DatePipe, 
          public authService: AuthService, public userService: KorisnikService, private smestajService: SmestajService) { 
    // let res = localStorage.getItem('token');
    // if(res != null){
    //   this.loggedUser.mejl = this.authService.getUsername(res);
    // }
    // else {
    //   this.loggedUser.mejl = ""
    // }
  }

  ngOnInit() {

    if(this.loggedUser.mejl != ""){
      this.userService.findByEmail(this.loggedUser.mejl).subscribe(
        e => {
          this.loggedUser = e;
        }
      );
    }

    // let smestaj = new SmestajnaJedinica()

    // let adresa = new Adresa;
    // adresa.grad = "Beograd"
    // adresa.zemlja = "Srbija"

    // smestaj.adresaDTO = adresa;
    // smestaj.cena = 150;
    // smestaj.ocena = 4;
    // let tip = new TipSmestaja();
    // tip.naziv = "Hotel"
    // smestaj.tipDTO = tip;
    // smestaj.opis = "Najbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja sobaNajbolja soba"

    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);
    // this.accommodationToShow.push(smestaj);

  }

  searchAccommodations(){
    this.accommodationSearch.pocetak = this.toModel(this.beginDate);
    this.accommodationSearch.kraj = this.toModel(this.endDate);

    this.smestajService.search(this.accommodationSearch).subscribe(
      s => this.accommodationToShow = s
    )
  }

  book(accommodation: SmestajnaJedinica){
    let reservation : Rezervacija = new Rezervacija();
    reservation.pocetak = this.accommodationSearch.pocetak;
    reservation.kraj = this.accommodationSearch.kraj;
    reservation.smestaj = accommodation;

    this.smestajService.book(reservation, this.loggedUser.id).subscribe(
      s => {

      }
    )
  }

  fromModel(date: string): NgbDateStruct {

    const parsedDate = /(\d\d\d\d)-(\d\d)-(\d\d)/.exec(date);
    if (parsedDate) {
      return <NgbDateStruct>{ year: Number(parsedDate[1]), month: Number(parsedDate[2]), day: Number(parsedDate[3]) };
    } else {
      return null;
    }
  }

  toModel(date: NgbDateStruct): string {
    if (date) {
      let dateString = date.year + '-' + date.month + '-' + date.day;
      return this.datepipe.transform(dateString, 'yyyy-MM-dd')
    } else {
      return null;
    }
  }

  vratiTrenutnuCenu(accommodation: SmestajnaJedinica){
    if(accommodation.cena == 0){
      return 100;
    }
    return accommodation.cena;
  }
}
