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
        this.zemlja = undefined;
        this.grad = undefined;
        this.ulica = undefined;
        this.broj = undefined;
        this.geografskaDuzina = 0;
        this.geografskaSirina = 0;
    }

}