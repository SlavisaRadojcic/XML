import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { KorisnikService } from 'app/service/korisnik/korisnik.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prikaz-korisnika',
  templateUrl: './prikaz-korisnika.component.html',
  styleUrls: ['./prikaz-korisnika.component.css']
})
export class PrikazKorisnikaComponent implements OnInit {

  users: Korisnik[];
  selectedUser: Korisnik;
  dropdownSettings = {};
  p: any;
  loggedUser: Korisnik = new Korisnik();

  constructor(private authService: AuthService, private userService : KorisnikService, 
    private router: Router) {
    // let res = localStorage.getItem('token');
    // if(res == null){
    //   this.router.navigate(['login']);
    // }
    // if(res != null){
    //   this.loggedUser.mejl = this.authService.getUsername(res);
    // }
  }

  ngOnInit() {
    
    // this.userService.findByEmail(this.loggedUser.mejl).subscribe(
    //   e => {
    //     this.loggedUser = e;
    //   }
    // );
    this.userService.findAll().subscribe(
      s => {
        this.users = s
      }
    );

    this.dropdownSettings = {
      singleSelection: true,
      itemsShowLimit: 3,
      allowSearchFilter: true
    };

  }

  changeStateOfUser(user:Korisnik, boolState: string){
    user.status = boolState;
    this.userService.changeStateOfUser(user.id, boolState).subscribe(
      s => {
        this.userService.findAll().subscribe(
          novi => {
            this.users = novi
          }
        );
      }
    );
  }

}
