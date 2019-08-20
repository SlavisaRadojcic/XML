import { Component, OnInit } from '@angular/core';
import { Korisnik } from 'app/model/korisnik/korisnik';
import { AuthService } from 'app/service/korisnik/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  user: Korisnik = new Korisnik();
  maxLength: number = 30;
  mailNotUnique: boolean = true;

  patternHigh: any = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{12,}$";

id: number;
postojiDugme: boolean = true;
private sub: any;
isDataAvailable: boolean;

  constructor(private auth: AuthService, private router: Router, private route: ActivatedRoute) {
    this.user = new Korisnik();
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.isDataAvailable = false;
      if(params['id'] != null){
        this.id = +params['id']; // (+) konvertuje string 'id' u broj
        //id postavljamo kao path parametar pomocu interpolacije stringa
        if(this.id != null )
        { 
          this.postojiDugme = false; //znaci da hoce da doda agenta
        }
      }
   });
  }

  register(){
    let message: string = this.proveriUnosKorisnik();
    if(message === "OK")
    {
      if(this.id == null){
      this.auth.signup(this.user)
          .subscribe(
            res => 
            {
              this.router.navigate(['login'])
            },
            err => {
              console.log("usao kao error");
              if (err.status === 400) { // bad request losi kredencijali
                console.log("usao 400");
                this.mailNotUnique = false;
            }
          }
        )
      }
      else{   //registruj agenta
        this.user.tip = "AGENT";
        this.auth.signupAgent(this.user).subscribe(
          ss =>{
              this.router.navigate(['login']);
          },
          e => {
            alert('error');
          }
        )
      }
    }
    else{
      alert(message);
    }
  }

  validateEmail(email: string) 
  {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

proveriUnosKorisnik(): string{
  if(this.user.mejl === "" || this.user.mejl.length > 60){
    return "Morate popuniti validnu e-mail adresu koja mora biti kraca od " + 60 + " karaktera";
  }

  if(!this.validateEmail(this.user.mejl)){
    return "Morate uneti validnu e-mail adresu";
  }

  if(this.user.ime === "" || this.user.ime.length > this.maxLength){
    return "Morate popuniti ime koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  if(this.user.prezime === "" || this.user.prezime.length > this.maxLength){
    return "Morate popuniti prezime koja mora biti kraca od " + this.maxLength + " karaktera";
  }

  return "OK";
}

}
