import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Login } from 'app/model/korisnik/login';
import { Korisnik } from 'app/model/korisnik/korisnik';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient,
    private router: Router) { }
  
    login(mejl:string, lozinka:string): Observable<Korisnik>{
      let login: Login = new Login();
      login.mejl = mejl;
      login.sifra = lozinka;
      const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
      return this.http.post<Korisnik>('/api/korisnici/login', login);
    }

    logout(): void {
      localStorage.removeItem('korisnik');
      this.router.navigate(['login']);
    }

}