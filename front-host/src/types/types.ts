import { GameState, RoundState } from "./enums";

export type Element = {
    children: JSX.Element | JSX.Element[] | string;
};

export type FallbackElement = Element & { fallback: () => JSX.Element };

export type Reducer = (state: IGameContext, action: any) => IGameContext;

export interface IGameContext {
    reconnect: (code: string, secret: string) => void;
    createGame: () => void;
    game: IGame;
    round?: IRound;
    players: IPlayers[];
}

export interface IGame {
    code?: string;
    secret?: string;
    state: GameState;
    roundDuration?: number;
    numberOfSongs?: number;
    playlist?: IPlaylist;
}

export interface IPlaylist {
    id: string;
    description: string;
    name: string;
    thumbnail: string;
    total_songs: number;
}

export interface IRound {
    id: number;
    state: RoundState;
    preview: string;
    answerId: string;
    alternatives: IAlternative[];
}

export interface IPlayers {
    id: number;
    name: string;
    vip: boolean;
    connected: boolean;
    points?: number;
    roundPoints?: number;
}

export interface IAlternative {
    id: string;
    name: string;
    artist: string;
    preview: string;
    thumbnail: string;
}
