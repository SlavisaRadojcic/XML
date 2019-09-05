import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';
import { Komentar } from 'app/model/smestajnaJedinica/komentar';

@Injectable()
export class KomentarService {

  baseUrl: string = environment.baseUrl + '/mikroservis-smestajne-jedinice/api/komentari';

  constructor(private http: HttpClient) { }

  updateReview(komentar: Komentar): Observable<Komentar> {
    return this.http.put<Komentar>(this.baseUrl + `/${komentar.id}`, {});
  }
  
  getUnapprovedUserReviews(): Observable<Komentar[]> {
    return this.http.get<Komentar[]>(this.baseUrl + "/neodobreni");
  }
}