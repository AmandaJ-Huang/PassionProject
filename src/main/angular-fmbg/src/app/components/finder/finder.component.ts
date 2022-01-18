import { Component, OnInit } from '@angular/core';
import { Boardgames } from 'src/app/models/boardgames';
import { BoardgamesService } from 'src/app/services/boardgames.service';
import {HttpParams} from "@angular/common/http";


@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.css']
})
export class FinderComponent implements OnInit {
  boardgames?: Boardgames[];

  constructor(private boardgamesService: BoardgamesService) {

  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const params = "min_age=10";
    this.findgames(params);
    console.log(params);
  }

  findgames(params: any): void {
    this.boardgamesService.findGames(params)
      .subscribe(
        data => {
          this.boardgames = data;
          console.log(data);
        }
      )
  }
}
