import { Component, OnInit } from '@angular/core';
import { KriterijumiPretrage } from 'app/model/smestajnaJedinica/kriterijumiPretrage';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { DatePipe } from '@angular/common';
import { SmestajService } from 'app/service/smestaj/smestaj.service';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { Adresa } from 'app/model/smestajnaJedinica/adresa';

@Component({
  selector: 'app-prikaz-rezervacija',
  templateUrl: './prikaz-rezervacija.component.html',
  styleUrls: ['./prikaz-rezervacija.component.css']
})
export class PrikazRezervacijaComponent implements OnInit {

  accommodationToShow : Rezervacija[] = [];

  loggedUser: Korisnik = new Korisnik();
  spisakOcena: string[] = ['1', '2', '3', '4', '5'];
  dropdownSettings:any;

  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  constructor(private route: ActivatedRoute, private router: Router, public datepipe: DatePipe, 
          public authService: AuthService, public userService: KorisnikService, private smestajService: SmestajService) { 
   this.loggedUser = JSON.parse(localStorage.getItem('token'));
  }

  getAll(){

    this.accommodationToShow = [];

    this.smestajService.preuzmiAktivneuRezervacije(this.loggedUser.id).subscribe(
      s => {
        s.forEach(element => {
          this.accommodationToShow.push(element);
        });
      }
    )

    this.smestajService.preuzmiIstorijuRezervacija(this.loggedUser.id).subscribe(
      s => {
        s.forEach(element => {
          this.accommodationToShow.push(element);
        });
      }
    )
  }

  ngOnInit() {

    this.dropdownSettings = {
      singleSelection: true
    };

    this.getAll();
  }

  cancel(reservation: Rezervacija){
    this.smestajService.otkazi(reservation).subscribe(
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

  addComment(accommodation){
    localStorage.setItem('reservation', JSON.stringify(accommodation));
    this.router.navigate(['oceni']);
  }

  selected(accommodation: Rezervacija){
    this.smestajService.postaviOcenu(accommodation, accommodation.ocena, this.loggedUser).subscribe(
      s => {
          this.getAll()
      }
    )
  }
}
