import { Component, OnInit } from '@angular/core';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { SmestajService } from 'app/service/smestaj/smestaj.service';

@Component({
  selector: 'app-prikaz-tipa-smestaja',
  templateUrl: './prikaz-tipa-smestaja.component.html',
  styleUrls: ['./prikaz-tipa-smestaja.component.css']
})
export class PrikazTipaSmestajaComponent implements OnInit {

  accommodationTypes: TipSmestaja[] = []
  newAccommodationType: TipSmestaja = new TipSmestaja();
  p1:any;
  p2:any;
  constructor(private accommodationService: SmestajService) {
   }

  ngOnInit() {
    this.accommodationService.getAllAccomodationTypes().subscribe(
      s =>{
        this.accommodationTypes = s;
      }
    )
  }

  addAccommodationType(){
    if(this.newAccommodationType.id == 0){
      this.accommodationService.createAccommodationType(this.newAccommodationType).subscribe(
        s => {
          this.accommodationTypes.push(s);
        }
      )
    }
    else{
      this.accommodationService.createAccommodationType(this.newAccommodationType).subscribe(
        s => { 
          this.accommodationTypes.forEach(element =>{
            if(element.id == s.id){
              element.naziv = s.naziv;
            }
          }
        )}
      )
    }
    this.newAccommodationType = new TipSmestaja();
  }

  deleteAccommodationType(accommodationType: TipSmestaja){
    this.accommodationService.deleteAccommodationType(accommodationType.id).subscribe(
      s => {
          let pomAccommodationType: TipSmestaja = new TipSmestaja();
          this.accommodationTypes.forEach(element => {
            if(element.id === accommodationType.id){
              pomAccommodationType = element;
            }
          })

          this.accommodationTypes.splice(this.accommodationTypes.indexOf(pomAccommodationType), 1)
      }
    )
  }

  editAccommodationType(accommodationType: TipSmestaja){
    this.newAccommodationType.naziv = accommodationType.naziv;
    this.newAccommodationType.id = accommodationType.id;
  }
}
