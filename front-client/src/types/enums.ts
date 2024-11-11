export enum EventType {
    SEND_ANSWER = "client/send-answer",
    UPDATE_AVATAR = "client/update-avatar",
    // Vip Actions
    CHANGE_PLAYLIST = "vip/change-playlist",
    CHANGE_TIMER = "vip/round-duration",
    CHANGE_AMOUNT = "vip/number-rounds",
    START_GAME = "vip/start-game",
    RESET_GAME = "vip/reset-game",
}

export enum BroadcastEvent {
    WELCOME = "welcome",
    UPDATE_GAME = "game/update",
    UPDATE_ROUND = "round/update",
    UPDATE_PLAYER = "player/update",
    PLAYER_ANSWER = "player/answer",
    UPDATE_AVATAR = "player/update-avatar",
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
    SUMMARY = "SUMMARY",
    FINISHED = "FINISHED",
}
