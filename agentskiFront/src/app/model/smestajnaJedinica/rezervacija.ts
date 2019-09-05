import { SmestajnaJedinica } from "./smestajnaJedinica";
import { Korisnik } from "../korisnik/korisnik";
import { Ocena } from "./ocena";

export class Rezervacija{
    id: number;
    cena: number;
    realizovana: boolean;
    korisnik: Korisnik;
    ocena: Ocena;
    pocetak: string;
    kraj: string;
    smestaj: SmestajnaJedinica;

    constructor(){
        this.id = 0;
        this.cena = 0;
        this.realizovana = false;
        this.korisnik = new Korisnik();
        this.ocena = new Ocena();
        this.pocetak = "";
        this.kraj = "";
        this.smestaj = new SmestajnaJedinica();
    }
}

