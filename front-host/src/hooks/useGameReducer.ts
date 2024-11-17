import { BroadcastEvent, GameState } from "../types/enums";
import { IGameContext, IMatchResult, IPlayers, IRoundResult } from "../types/types";

interface IPayloadEvent {
    type: BroadcastEvent;
    object: any;
}

const onWelcomeHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    localStorage.setItem("msc:code", object.id);
    localStorage.setItem("msc:secret", object.secret);

    return {
        ...state,
        game: object,
    };
};

const onDisconnectHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    localStorage.removeItem("msc:code");
    localStorage.removeItem("msc:secret");

    return {
        ...state,
        game: { state: GameState.NOT_CREATED },
        players: [] as IPlayers[],
        roundResult: [] as IRoundResult[],
        matchResult: [] as IMatchResult[],
    };
};

const onGameUpdateHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    return {
        ...state,
        game: object,
    };
};

const onMatchUpdateHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    return {
        ...state,
        match: object,
    };
};

const onMatchResultHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    return {
        ...state,
        matchResult: object,
    };
};

const onRoundUpdateHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    const { round } = state;

    return {
        ...state,
        round: {
            ...round,
            ...object,
        },
    };
};

const onRoundResultHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    return {
        ...state,
        roundResult: object,
    };
};

const onPlayerEnterHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    const { players } = state;

    return {
        ...state,
        players: [...players, object],
    };
};

const onPlayerUpdateHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    const { players } = state;
    const index = players.findIndex((p) => p.id === object.id);

    return {
        ...state,
        players: [...state.players.slice(0, index), object, ...state.players.slice(index + 1)],
    };
};

const onPlayerLeaveHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    const { players } = state;
    const index = players.findIndex((p) => p.id === object.id);

    return {
        ...state,
        players: [...state.players.slice(0, index), ...state.players.slice(index + 1)],
    };
};

export const useGameReducer = (state: IGameContext, payload: IPayloadEvent) => {
    switch (payload.type) {
        case BroadcastEvent.WELCOME:
            return onWelcomeHandler(state, payload);
        case BroadcastEvent.DISCONNECT:
            return onDisconnectHandler(state, payload);
        case BroadcastEvent.UPDATE_GAME:
            return onGameUpdateHandler(state, payload);
        case BroadcastEvent.UPDATE_MATCH:
            return onMatchUpdateHandler(state, payload);
        case BroadcastEvent.MATCH_RESULT:
            return onMatchResultHandler(state, payload);
        case BroadcastEvent.UPDATE_ROUND:
            return onRoundUpdateHandler(state, payload);
        case BroadcastEvent.ROUND_RESULT:
            return onRoundResultHandler(state, payload);
        case BroadcastEvent.NEW_PLAYER:
            return onPlayerEnterHandler(state, payload);
        case BroadcastEvent.UPDATE_PLAYER:
            return onPlayerUpdateHandler(state, payload);
        case BroadcastEvent.REMOVE_PLAYER:
            return onPlayerLeaveHandler(state, payload);
        default:
            return state;
    }
};
