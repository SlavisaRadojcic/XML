import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';
import { KriterijumiPretrage } from 'app/model/smestajnaJedinica/kriterijumiPretrage';
import { Observable } from 'rxjs/Observable';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { environment } from 'environments/environment.prod';

@Injectable()
export class SmestajService {

  baseUrl: string = environment.baseUrl + '/mikroservis-smestajne-jedinice';

  constructor(private http: HttpClient) { }

  book(reservation: Rezervacija, id: number): Observable<Rezervacija> {
    let params = new HttpParams();
    params = params.append('smestaj', reservation.smestaj.id.toString());
    params = params.append('korisnik', id.toString());
    return this.http.post<Rezervacija>(this.baseUrl + '/api/rezervacije', reservation, {params: params})
  }
  search(accommodationSearch: KriterijumiPretrage): Observable<SmestajnaJedinica[]> {
    return this.http.post<SmestajnaJedinica[]>(this.baseUrl + '/api/smestaji', accommodationSearch)
  }
}
