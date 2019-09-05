export class Komentar{
    id: number;
    tekst: string;
    odobren: boolean;

    constructor(){
        this.id = 0;
        this.tekst = "";
        this.odobren = false;
    }
}