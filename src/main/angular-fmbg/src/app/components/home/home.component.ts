import { Component, OnInit } from '@angular/core';
import { Boardgames } from 'src/app/models/boardgames';
import { BoardgamesService } from 'src/app/services/boardgames.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  boardgames?: Boardgames[];

  constructor(private boardgamesService: BoardgamesService) { }

  ngOnInit(): void {
  }

  toggleFinderTask() {
    console.log('toggle');
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
