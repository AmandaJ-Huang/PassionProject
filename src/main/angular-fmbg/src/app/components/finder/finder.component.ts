import { Component, OnInit } from '@angular/core';
import { Boardgames } from 'src/app/models/boardgames';
import { BoardgamesService } from 'src/app/services/boardgames.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.css']
})
export class FinderComponent implements OnInit {
  boardgames?: Boardgames[];
  games: FormGroup;

  constructor(private boardgamesService: BoardgamesService, 
    fb: FormBuilder) { 
      this.games = fb.group( 
        {}
      )
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
