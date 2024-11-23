import { GameState, MatchState, RoundState } from "./enums";

export type Element = {
    children: JSX.Element | JSX.Element[] | string;
};

export type FallbackElement = Element & { fallback: () => JSX.Element };

export type Reducer = (state: IGameContext, action: any) => IGameContext;

export interface IGameContext {
    connectClient: (code: string, user: string, avatar: IAvatar) => void;
    reconnectClient: (code: string, id: string, secret: string) => void;
    disconnectClient: () => void;
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
    results: IRoundResult[];
    matchResult?: IMatchResult;
}

export interface IPlayer {
    sessionId?: string;
    id?: string;
    name: string;
    avatar?: IAvatar;
    secret?: string;
    vip?: boolean;
}

export interface IAvatar {
    [key: string]: string;
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

export interface IRound {
    id: string;
    roundNumber: number;
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
    position: number;
    totalPoints: number;
    totalCorrectAnswers: number;
    averageGuessTime: number;
}
