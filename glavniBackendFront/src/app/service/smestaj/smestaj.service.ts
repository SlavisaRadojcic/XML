import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { KriterijumiPretrage } from 'app/model/smestajnaJedinica/kriterijumiPretrage';
import { Observable } from 'rxjs/Observable';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { environment } from 'environments/environment.prod';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { Usluga } from 'app/model/smestajnaJedinica/usluga';
import { Komentar } from 'app/model/smestajnaJedinica/komentar';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Injectable()
export class SmestajService {

  baseUrl: string = environment.baseUrl + '/mikroservis-smestajne-jedinice';

  constructor(private http: HttpClient) { }

  getAll(): Observable<SmestajnaJedinica[]>{
    return this.http.get<SmestajnaJedinica[]>(this.baseUrl + '/api/smestaji');
  }

  book(reservation: Rezervacija, id: number): Observable<Rezervacija> {
    let params = new HttpParams();
    params = params.append('smestaj', reservation.smestaj.id.toString());
    params = params.append('korisnik', id.toString());
    return this.http.post<Rezervacija>(this.baseUrl + '/api/rezervacije', reservation, {params: params})
  }
  search(accommodationSearch: KriterijumiPretrage): Observable<SmestajnaJedinica[]> {
    return this.http.post<SmestajnaJedinica[]>(this.baseUrl + '/api/smestaji', accommodationSearch)
  }
  otkazi(reservation: Rezervacija): Observable<{}> {
    return this.http.delete(this.baseUrl + `/api/rezervacije/${reservation.id}`);
  }

  postaviOcenu(rezervacija: Rezervacija, ocena: number, korisnik: Korisnik){
    let params = new HttpParams();
    params = params.append('ocena', ocena.toString());
    params = params.append('korisnik', korisnik.id.toString());
    return this.http.post(environment.baseUrl+ '/mikroservis-ocene' + `/api/rezervacije/${rezervacija.id}/ocene`,{}, {params: params});
  }

  preuzmiIstorijuRezervacija(korisnikId : number): Observable<Rezervacija[]>{
    let params = new HttpParams();
    params = params.append('korisnik', korisnikId.toString());
    return this.http.get<Rezervacija[]>(this.baseUrl + `/api/rezervacije/istorija`, {params:params})
  }

  preuzmiAktivneuRezervacije(korisnikId : number): Observable<Rezervacija[]>{
    let params = new HttpParams();
    params = params.append('korisnik', korisnikId.toString());
    return this.http.get<Rezervacija[]>(this.baseUrl + `/api/rezervacije/aktivne`, {params:params})
  }

  posaljiKomentar(komentar: Komentar, rezervacijaId: number): Observable<Komentar>{
    return this.http.post<Komentar>( this.baseUrl +  `/api/rezervacije/${rezervacijaId}/komentar`, komentar)
  }

  getAllAccomodationTypes(): Observable<TipSmestaja[]> {
    return this.http.get<TipSmestaja[]>(this.baseUrl + "/api/tipovi");
  }

  getAllAddiionalServices(): Observable<Usluga[]> {
    return this.http.get<Usluga[]>(this.baseUrl + "/api/usluge");
  }

  createAccommodationType(newAccommodationType: TipSmestaja): Observable<TipSmestaja> {
    return this.http.post<TipSmestaja>(this.baseUrl + "/api/tipovi", newAccommodationType);
  }

  createAdditionalService(newAdditionaService: Usluga): Observable<Usluga> {
    return this.http.post<Usluga>(this.baseUrl + "/api/usluge", newAdditionaService);
  }

  deleteAccommodationType(idAccommodationType: number): Observable<{}> {
    return this.http.delete(this.baseUrl + `/api/tipovi/${idAccommodationType}`);
  }

  deleteAdditionalService(idAdditionalService: number): Observable<{}> {
    return this.http.delete(this.baseUrl + `/api/usluge/${idAdditionalService}`);
  }

  updateAccomodationType(accommodationType: TipSmestaja): Observable<TipSmestaja> {
    return this.http.put<TipSmestaja>(this.baseUrl + `/api/tipovi/${accommodationType.id}`, accommodationType);
  }

  updateAddiionalService(additionalService: Usluga): Observable<Usluga> {
    return this.http.put<Usluga>(this.baseUrl + `/api/usluge/${additionalService.id}`, additionalService);
  }
}
