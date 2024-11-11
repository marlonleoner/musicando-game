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
    UPDATE_ROUND = "round/update",
    UPDATE_GAME = "game/update",
    NEW_PLAYER = "player/new",
    UPDATE_PLAYER = "player/update",
    REMOVE_PLAYER = "player/remove",
    RESPONSE_ANSWER = "response-answer",
    // Host Actions
    CHANGE_PLAYLIST = "change-playlist",
    CHANGE_TIMER = "change-timer",
    CHANGE_AMOUNT = "change-amount",
}

export enum GameState {
    NOT_CREATED = "NOT_CREATED",
    LOBBY = "LOBBY",
    PRE_LIVE = "PRE_LIVE",
    LIVE = "LIVE",
    FINISHED = "FINISHED",
}

export enum RoundState {
    PRE_LIVE = "PRE_LIVE",
    LIVE = "LIVE",
    POST_LIVE = "POST_LIVE",
}
