import { Component, OnInit } from '@angular/core';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { SmestajService } from 'app/service/smestaj/smestaj.service';
import { Komentar } from 'app/model/smestajnaJedinica/komentar';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';

@Component({
  selector: 'app-oceni-rezervaciju',
  templateUrl: './oceni-rezervaciju.component.html',
  styleUrls: ['./oceni-rezervaciju.component.css']
})
export class OceniRezervacijuComponent implements OnInit {

  private rezervacija: Rezervacija = new Rezervacija();
  private loggedKorisnik: Korisnik = new Korisnik();
  private komentar: Komentar = new Komentar();

  constructor(private smestajService: SmestajService) {
    this.loggedKorisnik = JSON.parse(localStorage.getItem('token'))
   }

  ngOnInit() {
    this.rezervacija = JSON.parse(localStorage.getItem('reservation'));
  }

  posaljiKomentar(){
    this.smestajService.posaljiKomentar(this.komentar, this.rezervacija.id).subscribe(
      s => {
        alert("Uspesno");
      }
    )
  }

  vratiTrenutnuCenu(accommodation: SmestajnaJedinica){
    if(accommodation.cena == 0){
      return 100;
    }
    return accommodation.cena;
  }

}
