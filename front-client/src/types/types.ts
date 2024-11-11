import { GameState, RoundState } from "./enums";

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
    playerAvatar?: string;
    player?: IPlayer;
    game?: IGame;
    round?: IRound;
    response?: IRoundResponse;
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
    code: string;
    state: GameState;
    roundDuration: number;
    numberOfSongs: number;
    playlist: IPlaylist;
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

export interface IRoundResponse {
    correctAnswer: boolean;
    roundPoints: number;
}
