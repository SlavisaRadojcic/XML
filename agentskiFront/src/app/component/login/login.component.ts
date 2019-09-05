import { Component, OnInit } from '@angular/core';
import { Login } from 'app/model/korisnik/login';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/user/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Login = new Login();
  loggedUser: Korisnik = new Korisnik();
  constructor(private auth: AuthService, private router: Router) { 
  }

  ngOnInit() {
  }

  login(){
  localStorage.removeItem('korisnik');
  this.auth.login(this.user.mejl, this.user.sifra)
      .subscribe(
        res => 
        {
          console.log(res);
          localStorage.setItem('korisnik', JSON.stringify(res));
          this.router.navigate(['smestajne-jedinice']);
        },
        err => {
          alert("Wrong email or password");
          console.log("usao kao error za logovanje");
        }
      );
  }

}
