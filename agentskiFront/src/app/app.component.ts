import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from './model/korisnik/korisnik';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {

  public loggedIn: boolean = false;

  constructor(/*private authService: AuthService,*/ private router: Router){
    let res: Korisnik = JSON.parse(localStorage.getItem('korisnik'));
    if(res != null){
      this.loggedIn = true;
    }
  }

  logOut(){
    //this.authService.logout();
    localStorage.removeItem('korisnik');
    this.loggedIn = false;
    this.router.navigate(['login']);
  }
  
}
