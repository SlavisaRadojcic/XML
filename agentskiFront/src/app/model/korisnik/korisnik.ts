export class Korisnik{

    public id: number;
	public ime: string;
	public prezime: string;
	public mejl: string;
	public sifra: string;
    public status: string;
	public poslovniMaticniBroj: string;
	public tip: string;

    constructor(){
        this.id = 0;
        this.ime = "";
        this.prezime = "";
        this.mejl = "";
        this.sifra = "";
        this.status = "AKTIVAN";
        this.poslovniMaticniBroj = "";
        this.tip = "KORISNIK";
    }
}