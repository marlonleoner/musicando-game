export enum EventType {
    // Connection Actions
    CONNECTION = "host/connection",
    START_GAME = "host/start-game",
    RESET_GAME = "host/reset-game",
    PRE_ROUND = "host/pre-round",
    START_ROUND = "host/start-round",
    FINISH_ROUND = "host/finish-round",
    FINISH_GAME = "host/finish-game",
}

export enum BroadcastEvent {
    WELCOME = "welcome",
    DISCONNECT = "disconnect",
    UPDATE_GAME = "game/update",
    UPDATE_MATCH = "match/update",
    MATCH_RESULT = "match/result",
    UPDATE_ROUND = "round/update",
    ROUND_RESULT = "round/result",
    NEW_PLAYER = "player/new",
    UPDATE_PLAYER = "player/update",
    REMOVE_PLAYER = "player/remove",
}

export enum GameState {
    NOT_CREATED = "NOT_CREATED",
    LOBBY = "LOBBY",
    PRE_LIVE = "PRE_LIVE",
    LIVE = "LIVE",
    FINISHED = "FINISHED",
}

export enum MatchState {
    PREPARING = "PREPARING",
    LIVE = "LIVE",
    FINISHED = "FINISHED",
    PAUSED = "PAUSED",
}

export enum RoundState {
    PRE_LIVE = "PRE_LIVE",
    LIVE = "LIVE",
    POST_LIVE = "POST_LIVE",
}
