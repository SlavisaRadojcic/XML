import { SmestajnaJedinica } from "./smestajnaJedinica";

export class Rezervacija{
    id: number;
    cena: number;
    realizovana: boolean;
    korisnik: number;
    ocena: number;
    pocetak: string;
    kraj: string;
    smestaj: SmestajnaJedinica;

    constructor(){
        this.id = 0;
        this.cena = 0;
        this.realizovana = false;
        this.korisnik = 0;
        this.ocena = 0;
        this.pocetak = "";
        this.kraj = "";
        this.smestaj = new SmestajnaJedinica();
    }
}

