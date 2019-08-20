export class Adresa{

    id: number;
    zemlja: string;
    grad: string;
    ulica: string;
    broj: number;
    geografskaDuzina: number;
    geografskaSirina: number;


    constructor(){
        this.id = 0;
        this.zemlja = "";
        this.grad = "";
        this.ulica = "";
        this.broj = 0;
        this.geografskaDuzina = 0;
        this.geografskaSirina = 0;
    }

}