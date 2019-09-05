import { Adresa } from "./adresa";
import { TipSmestaja } from "./tipSmestaja";
import { Usluga } from "./usluga";

export class SmestajnaJedinica{

   id: number;
   opis: string;
   kapacitet: number;
   cena: number;
   dozvoljenoOtkazivanje: boolean;
   brojDanaZaOtkazivanje: number;
   ocena: number;
   agent: number;
   adresaDTO: Adresa;
   tipDTO: TipSmestaja;
   uslugeDTO: Usluga[];

   constructor(){
       this.id = 0;
       this.opis = "";
       this.kapacitet = undefined;
       this.cena = undefined;
       this.dozvoljenoOtkazivanje = false;
       this.brojDanaZaOtkazivanje = undefined;
       this.ocena = 0;
       this.agent = 0;
       this.adresaDTO = new Adresa();
       this.tipDTO = new TipSmestaja();
       this.uslugeDTO = [];
   }
   
}