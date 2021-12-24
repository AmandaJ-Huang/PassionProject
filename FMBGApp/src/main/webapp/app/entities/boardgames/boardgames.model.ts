export interface IBoardgames {
  id?: number;
  thumbnail_url?: string | null;
  primary_name?: string | null;
  minplayers?: number | null;
  maxplayers?: number | null;
  suggested_numplayers?: number | null;
  playingtime?: number | null;
  suggested_playerage?: number | null;
  rating?: number | null;
  rank?: number | null;
  averageweight?: number | null;
  category?: string | null;
}

export class Boardgames implements IBoardgames {
  constructor(
    public id?: number,
    public thumbnail_url?: string | null,
    public primary_name?: string | null,
    public minplayers?: number | null,
    public maxplayers?: number | null,
    public suggested_numplayers?: number | null,
    public playingtime?: number | null,
    public suggested_playerage?: number | null,
    public rating?: number | null,
    public rank?: number | null,
    public averageweight?: number | null,
    public category?: string | null
  ) {}
}

export function getBoardgamesIdentifier(boardgames: IBoardgames): number | undefined {
  return boardgames.id;
}
