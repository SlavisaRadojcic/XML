import { Component, OnInit } from '@angular/core';
import { Usluga } from 'app/model/smestajnaJedinica/usluga';
import { SmestajService } from 'app/service/smestaj/smestaj.service';

@Component({
  selector: 'app-prikaz-usluga',
  templateUrl: './prikaz-usluga.component.html',
  styleUrls: ['./prikaz-usluga.component.css']
})
export class PrikazUslugaComponent implements OnInit {

  additionalServices: Usluga[] = []
  newAdditionalService: Usluga = new Usluga();
  p1:any;
  p2:any;
  constructor(private accommodationService: SmestajService) {
   }

  ngOnInit() {
    
    this.accommodationService.getAllAddiionalServices().subscribe(
      s =>{
        this.additionalServices = s;
      }
    )
  }

  addAdditionalService(){
    if(this.newAdditionalService.id == 0){
      this.accommodationService.createAdditionalService(this.newAdditionalService).subscribe(
        s => {
          this.additionalServices.push(s);          
        }
      )
    }
    else{
      this.accommodationService.updateAddiionalService(this.newAdditionalService).subscribe(
        s => {
          this.additionalServices.forEach(element =>{
            if(element.id == s.id){
              element.naziv = s.naziv;
              element.opis = s.opis
            }
          }
        )        
        }
      )
    }
    this.newAdditionalService = new Usluga();
  }

  deleteAdditionalService(additionalService: Usluga){
    this.accommodationService.deleteAdditionalService(additionalService.id).subscribe(
      s => {
        let pomAdditionalService: Usluga = new Usluga();
        this.additionalServices.forEach(element => {
          if(element.id === additionalService.id){
            pomAdditionalService = element;
          }
        })
    
        this.additionalServices.splice(this.additionalServices.indexOf(pomAdditionalService), 1)
      }
    )
  }

  editAdditionalService(additionalService: Usluga){
    this.newAdditionalService.id = additionalService.id;
    this.newAdditionalService.naziv = additionalService.naziv;
    this.newAdditionalService.opis = additionalService.opis;
  }

}