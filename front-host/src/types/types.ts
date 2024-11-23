import { GameState, MatchState, RoundState } from "./enums";

export type Element = {
    children: JSX.Element | JSX.Element[] | string;
};

export type FallbackElement = Element & { fallback: () => JSX.Element };

export type Reducer = (state: IGameContext, action: any) => IGameContext;

export interface IGameContext {
    reconnect: (id: string, secret: string) => void;
    createGame: () => void;
    game: IGame;
    match?: IMatch;
    round?: IRound;
    players: IPlayers[];
    roundResult: IRoundResult[];
    matchResult: IMatchResult[];
}

export interface IGame {
    id?: string;
    secret?: string;
    state: GameState;
}

export interface IMatch {
    roundDuration?: number;
    numberOfSongs?: number;
    playlist?: IPlaylist;
    state: MatchState;
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
    id: string;
    name: string;
    avatar: IAvatar;
    vip: boolean;
    connected: boolean;
    points?: number;
    roundPoints?: number;
}

export interface IAvatar {
    style: string;
    accessories: string;
    facialHair: string;
    eye: string;
    eyebrow: string;
    mouth: string;
    top: string;
    hairColor: string;
    clothe: string;
    clotheColor: string;
    skinColor: string;
}

export interface IAlternative {
    id: string;
    name: string;
    artist: string;
    preview: string;
    thumbnail: string;
}

export interface IRoundResult {
    roundId: string;
    playerId: string;
    correct: boolean;
    points: number;
    guessTime: number;
}

export interface IMatchResult {
    playerId: string;
    position: number;
    totalPoints: number;
    totalCorrectAnswers: number;
    averageGuessTime: number;
}
