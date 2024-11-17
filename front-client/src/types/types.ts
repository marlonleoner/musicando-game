import { GameState, MatchState, RoundState } from "./enums";

export type Element = {
    children: JSX.Element | JSX.Element[] | string;
};

export type FallbackElement = Element & { fallback: () => JSX.Element };

export type Reducer = (state: IGameContext, action: any) => IGameContext;

export interface IGameContext {
    connectClient: (code: string, user: string, avatar: string) => void;
    reconnectClient: (code: string, id: string, secret: string) => void;
    changePlaylist: (playlist: IPlaylist) => void;
    changeRoundDuration: (timer: number) => void;
    changeNumberOfRounds: (amount: number) => void;
    startGame: () => void;
    resetGame: () => void;
    sendAnswer: (answerId: string) => void;
    player?: IPlayer;
    game?: IGame;
    match?: IMatch;
    round?: IRound;
    roundResult?: IRoundResult;
    matchResult?: IMatchResult;
}

export interface IPlayer {
    sessionId?: string;
    id?: string;
    name: string;
    avatar?: string;
    secret?: string;
    vip?: boolean;
}

export interface IGame {
    id: string;
    state: GameState;
}

export interface IMatch {
    roundDuration?: number;
    numberOfSongs?: number;
    playlistId: string;
    state: MatchState;
}

// private Song answer;
export interface IRound {
    id: number;
    state: RoundState;
    preview: string;
    alternatives: IAlternative[];
    answer?: IAlternative;
}

export interface IAlternative {
    id: string;
    name: string;
    artist: string;
    preview: string;
    thumbnail: string;
}

export interface IPlaylist {
    id: string;
    description: string;
    name: string;
    thumbnail: string;
    total_songs: number;
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
    correctAnswers: boolean;
    totalPoints: number;
    totalGuessTime: number;
}
