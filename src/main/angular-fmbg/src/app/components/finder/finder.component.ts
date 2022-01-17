import { Component, OnInit } from '@angular/core';
import { Boardgames } from 'src/app/models/boardgames';
import { BoardgamesService } from 'src/app/services/boardgames.service';
import { FormControl, FormGroup } from "@angular/forms";

@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.css']
})
export class FinderComponent implements OnInit {
  boardgames?: Boardgames[];
  categories: any;
  mechanics: any;
  players: any;
  minage: any;
  orderby: any;
  finderForm: FormGroup | any;
  profileForm: Boolean | undefined;

  constructor(private boardgamesService: BoardgamesService) {
    let finderForm = new FormGroup({
      categories: new FormControl(''),
      mechanics: new FormControl(''),
      players: new FormControl(''),
      minage: new FormControl(''),
      orderby: new FormControl('')

    });
  }

  ngOnInit(): void {
  }

  findgames(): void {
    this.boardgamesService.findGames()
      .subscribe(
        data => {
          this.boardgames = data;
          console.log(data);
        }
      )
  }
}
