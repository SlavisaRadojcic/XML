import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { environment } from 'environments/environment';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Login } from 'app/model/korisnik/login';


@Injectable()
export class AuthService {
  baseUrl: string = environment.baseUrl + '/mikroservis-korisnici';

  constructor(private http: HttpClient,
    private router: Router) { }

    getRoles(token: string) {
      let jwtData = token.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      //console.log(decodedJwtData);
      //console.log(decodedJwtData.auth[0].authority);
      //console.log(decodedJwtData.sub); //username
      return decodedJwtData.auth[0].authority; //you can access role or username
    }
  
    getUsername(token: string) : string{
      let jwtData = token.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      //console.log(decodedJwtData);
      //console.log(decodedJwtData.auth[0].authority);
      //console.log(decodedJwtData.sub); //username
      return decodedJwtData.sub; //you can access role or username
    }
  
    login(mejl:string, lozinka:string): Observable<string>{
      let login: Login = new Login();
      login.mejl = mejl;
      login.sifra = lozinka;
      const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
      return this.http
        .post<string>(this.baseUrl + '/uloguj', login);
    }

    signup(registration: Korisnik): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/registruj', registration);
    }

    signupAgent(registration: Korisnik): Observable<{}>{
      return this.http
        .post<{}>(this.baseUrl + '/api/agenti', registration);
    }

    registerAdmin(id: number, registration: Korisnik): Observable<string>{
      return this.http
        .post<string>(this.baseUrl + `/users/register/${id}`, registration);
    }

    logout(): void {
      // localStorage.removeItem('token');
      // localStorage.removeItem('reservation');
      // localStorage.removeItem('beginDate');
      // localStorage.removeItem('endDate');
      this.router.navigate(['login']);
    }

}