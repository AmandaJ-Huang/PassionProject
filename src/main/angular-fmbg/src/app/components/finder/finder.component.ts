import { Component, OnInit } from '@angular/core';
import { Boardgames } from 'src/app/models/boardgames';
import { BoardgamesService } from 'src/app/services/boardgames.service';

@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.css']
})
export class FinderComponent implements OnInit {
  boardgames?: Boardgames[];

  constructor(private boardgamesService: BoardgamesService) { }

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
