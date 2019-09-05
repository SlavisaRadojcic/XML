import { Component, OnInit } from '@angular/core';
import { Login } from 'app/model/korisnik/login';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { Router } from '@angular/router';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Login = new Login();
  loggedUser: Korisnik = new Korisnik();
  constructor(private auth: AuthService, private router: Router, private userService: KorisnikService, private authService: AuthService) { 
  }

  ngOnInit() {
  }

  login(){
  localStorage.removeItem('token');
  this.auth.login(this.user.mejl, this.user.sifra)
      .subscribe(
        res => 
        {
          console.log(res);
          this.loggedUser = res;
          localStorage.setItem('token', JSON.stringify(res));

          if(this.loggedUser.tip == "KORISNIK"){
            this.router.navigate(['smestajneJedinice']);
          }
          else{ //znaci admin je u pitanju
            this.router.navigate(['korisnici']);
          }
          
        },
        err => {
          alert("Wrong email or password");
          console.log("usao kao error za logovanje");
        }
      );
  }

}