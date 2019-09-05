import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { TipSmestaja } from 'app/model/smestajnaJedinica/tipSmestaja';
import { Usluga } from 'app/model/smestajnaJedinica/usluga';
import { SmestajnaJedinica } from 'app/model/smestajnaJedinica/smestajnaJedinica';
import { Rezervacija } from 'app/model/smestajnaJedinica/rezervacija';

@Injectable()
export class SmestajnaJedinicaService {

  constructor(private http: HttpClient) { }

  findAllTipovi(): Observable<TipSmestaja[]> {
    return this.http.get<TipSmestaja[]>(`/api/smestaji/tipovi`);
  }

  findAllUsluge(): Observable<Usluga[]> {
    return this.http.get<Usluga[]>(`/api/smestaji/usluge`);
  }

  add(smestaj: SmestajnaJedinica, idAgent: number): Observable<SmestajnaJedinica[]> {
    return this.http.post<SmestajnaJedinica[]>(`/api/smestaji?agent=${idAgent}`, smestaj);
  }

  getAll(agentId: number): Observable<SmestajnaJedinica[]> {
    let params = new HttpParams();
    params = params.append('agent', agentId.toString());
    return this.http.get<SmestajnaJedinica[]>("/api/smestaji", {params: params});
  }

  book(reservation: Rezervacija, korisnikId: number): Observable<Rezervacija> {
    return this.http.post<Rezervacija>(`/api/smestaji/${korisnikId}/rezervacije`, reservation);
  }

  getReservations(idSmestaja: number): Observable<Rezervacija[]> {
    return this.http.get<Rezervacija[]>(`/api/smestaji/${idSmestaja}/rezervacije`);
  }

  updateResevation(rezervacija: Rezervacija): Observable<Rezervacija> {
    return this.http.post<Rezervacija>(`/api/smestaji/${rezervacija.smestaj.id}/rezervacije/${rezervacija.id}`, {});
  }


}
