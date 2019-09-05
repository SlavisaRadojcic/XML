import { Component, OnInit } from '@angular/core';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { SmestajnaJedinicaService } from 'app/service/smestajnaJedinica/smestajna-jedinica.service';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-prikaz-smestajne-jedinice',
  templateUrl: './prikaz-smestajne-jedinice.component.html',
  styleUrls: ['./prikaz-smestajne-jedinice.component.css']
})
export class PrikazSmestajneJediniceComponent implements OnInit {

  smestajnaJedinica: SmestajnaJedinica = new SmestajnaJedinica()

  beginDate: NgbDate;
  endDate: NgbDate;
  accommodationToShow : Rezervacija[] = [];

  loggedUser: Korisnik = new Korisnik();

  maxLength: number = 30;
  now: Date = new Date();
  minDate: any;
  maxDate: any;

  constructor(private route: ActivatedRoute, private router: Router, public datepipe: DatePipe, private smestajService: SmestajnaJedinicaService) { 
    this.loggedUser = JSON.parse(localStorage.getItem('korisnik'));
    this.smestajnaJedinica = JSON.parse(localStorage.getItem('smestajna-jedinica'))
  }

  ngOnInit() {
    this.smestajService.getReservations(this.smestajnaJedinica.id).subscribe(
      s => {
        this.accommodationToShow = s;
      }
    )
  }

  book(){
    let reservation : Rezervacija = new Rezervacija();
    reservation.pocetak = this.toModel(this.beginDate)
    reservation.kraj = this.toModel(this.endDate);
    reservation.smestaj = this.smestajnaJedinica;
    reservation.korisnik = this.loggedUser;

    this.smestajService.book(reservation, reservation.smestaj.id).subscribe(
      s => {
        alert("Uspesno rezervisano")
      }
    )
  }

  realizovana(accommodation: Rezervacija){
    this.smestajService.updateResevation(accommodation).subscribe(
      s => {
        alert("Realizovana");
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

