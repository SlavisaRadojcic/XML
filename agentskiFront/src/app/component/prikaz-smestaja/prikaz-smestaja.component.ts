import { Component, OnInit } from '@angular/core';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { SmestajnaJedinicaService } from 'app/service/smestajnaJedinica/smestajna-jedinica.service';

@Component({
  selector: 'app-prikaz-smestaja',
  templateUrl: './prikaz-smestaja.component.html',
  styleUrls: ['./prikaz-smestaja.component.css']
})
export class PrikazSmestajaComponent implements OnInit {

  accommodationToShow : SmestajnaJedinica[] = [];

  loggedUser: Korisnik = new Korisnik();

  constructor(private route: ActivatedRoute, private router: Router,
          public authService: AuthService, private smestajService: SmestajnaJedinicaService) { 
    this.loggedUser = JSON.parse(localStorage.getItem('korisnik'));
  }

  ngOnInit() {

    this.smestajService.getAll(this.loggedUser.id).subscribe(
      s => {
        this.accommodationToShow = s;
      }
    )
  }

  prikazSmestaja(accommodation: SmestajnaJedinica){
    localStorage.setItem('smestajna-jedinica', JSON.stringify(accommodation));
    this.router.navigate(['prikaz-smestajne-jedinice'])
  }

  vratiTrenutnuCenu(accommodation: SmestajnaJedinica){
    if(accommodation.cena == 0){
      return 100;
    }
    return accommodation.cena;
  }
}
