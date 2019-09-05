import { Component, OnInit } from '@angular/core';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { Usluga } from 'app/model/smestajnaJedinica/usluga';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/user/auth.service';
import { SmestajnaJedinicaService } from 'app/service/smestajnaJedinica/smestajna-jedinica.service';

@Component({
  selector: 'app-dodavanje-smestajne-jedinice',
  templateUrl: './dodavanje-smestajne-jedinice.component.html',
  styleUrls: ['./dodavanje-smestajne-jedinice.component.css']
})
export class DodavanjeSmestajneJediniceComponent implements OnInit {

  dropdownSettingsAdditionalServices;
  dropdownSettingsAccommodationType;
  additionalServices: Usluga[] = [];
  accommodationTypes: TipSmestaja[] = [];
  additionalServicesDropDown: string[] = [];
  accommodationTypeDropDown: string[] = [];
  selectAdditionalServices: string[] = [];
  selectedAccommodationType: string;

  accommodation: SmestajnaJedinica = new SmestajnaJedinica();
  loggedUser: Korisnik = new Korisnik();


  constructor(private smestajnaJedinicaService: SmestajnaJedinicaService, private router: Router, private authService: AuthService) { 
    let res: Korisnik = JSON.parse(localStorage.getItem('korisnik'));
    this.loggedUser = res;
}

  ngOnInit() {
    this.dropdownSettingsAdditionalServices = {
      singleSelection: false,
      itemsShowLimit: 10,
      allowSearchFilter: true
    };
    this.dropdownSettingsAccommodationType = {
      singleSelection: true,
      itemsShowLimit: 10,
      allowSearchFilter: true
    };

    this.smestajnaJedinicaService.findAllUsluge().subscribe(
      s => {
        this.additionalServices = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.naziv);
        })
        this.additionalServicesDropDown = pom;
      }
    )
    this.smestajnaJedinicaService.findAllTipovi().subscribe(
      s => {
        this.accommodationTypes = s;
        let pom = [];
        s.forEach(element => {
          pom.push(element.naziv);
        })
        this.accommodationTypeDropDown = pom;
      }
    )
   }

  save(){ 
    if(this.ifInputIsGood()){
      this.accommodationTypes.forEach(element => {
        if(element.naziv == this.selectedAccommodationType){
          this.accommodation.tipDTO = element;
        }
      })

      this.additionalServices.forEach(additionalService => {
        this.selectAdditionalServices.forEach(stringAdditionalService => {
          if(additionalService.naziv == stringAdditionalService){
            this.accommodation.uslugeDTO.push(additionalService);
          }
        })
      })

      if(this.accommodation.brojDanaZaOtkazivanje == 0 || this.accommodation.brojDanaZaOtkazivanje == undefined){
        this.accommodation.dozvoljenoOtkazivanje = false
      }
      else{
        this.accommodation.dozvoljenoOtkazivanje = true;
      }

      this.smestajnaJedinicaService.add(this.accommodation, this.loggedUser.id).subscribe(
        succ => {
          this.router.navigate(['smestajne-jedinice']);
        }
      )

      
    }
  }


  ifInputIsGood(){
    if(this.accommodation.adresaDTO.zemlja == ""){
      alert("You must enter country");
      return false;
    }
    if(this.accommodation.adresaDTO.grad == ""){
      alert("You must enter city");
      return false;
    }
    if(this.accommodation.adresaDTO.ulica == ""){
      alert("You must enter street");
      return false;
    }
    if(this.accommodation.adresaDTO.broj == 0 || this.accommodation.adresaDTO.broj == undefined){
      alert("You must enter number");
      return false;
    }
    if(this.accommodation.opis == ""){
      alert("You must enter description");
      return false;
    }

    return true;
  }

}
