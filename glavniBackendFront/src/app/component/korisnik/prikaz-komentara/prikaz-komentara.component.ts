import { Component, OnInit } from '@angular/core';
import { Komentar } from 'app/model/smestajnaJedinica/komentar';
import { KomentarService } from 'app/service/smestaj/komentar.service';

@Component({
  selector: 'app-prikaz-komentara',
  templateUrl: './prikaz-komentara.component.html',
  styleUrls: ['./prikaz-komentara.component.css']
})
export class PrikazKomentaraComponent implements OnInit {

  komentari: Komentar[] = [];

  constructor(private komentarService: KomentarService) { }

  ngOnInit() {
    this.komentarService.getUnapprovedUserReviews().subscribe(
      s => {
        this.komentari = s;
      }
    )
  }

  approveComment(review: Komentar){
    if(confirm("Odobri komentar?")) {
      review.odobren = true;
      this.komentarService.updateReview(review).subscribe(
        s => {
          let pomReview: Komentar = new Komentar();
          this.komentari.forEach(element => {
            if(element.id === review.id){
              pomReview = element;
            }
          })
      
          this.komentari.splice(this.komentari.indexOf(pomReview), 1)
        }
      )
    }
  }

}