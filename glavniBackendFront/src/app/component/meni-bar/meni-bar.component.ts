import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-meni-bar',
  templateUrl: './meni-bar.component.html',
  styleUrls: ['./meni-bar.component.css']
})
export class MeniBarComponent implements OnInit {

  constructor(private authService: AuthService, private userService: KorisnikService, private router: Router){
    let res = JSON.parse(localStorage.getItem('token'));
    if(res != null){
      this.loggedIn = true;
      this.loggedUser = res;
      if(this.loggedUser.tip == "ADMINISTRATOR"){
        this.isAdmin = true
      }
    }
  }


  ngOnInit() {
  }
  public loggedIn: boolean = false;
  isAdmin = false;
  loggedUser: Korisnik = new Korisnik();


  logOut(){
    this.authService.logout();
    this.loggedIn = false;
    this.router.navigate(['login']);
  }

  goToHome(){
    if(this.isAdmin){
      this.router.navigate(['korisnici']);
    }
    else{
      this.router.navigate(['smestajneJedinice']);
    }
  }
}