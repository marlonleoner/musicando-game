import { BroadcastEvent, RoundState } from "../types/enums";
import { IGameContext } from "../types/types";

interface IPayloadEvent {
    type: BroadcastEvent;
    object: any;
}

const onWelcomeHandler = (state: IGameContext, object: any) => {
    localStorage.setItem("msc:id", object.id);
    localStorage.setItem("msc:secret", object.secret);

    return {
        ...state,
        player: object,
    };
};

const onGameUpdate = (state: IGameContext, object: any) => {
    localStorage.setItem("msc:code", object.code);

    return {
        ...state,
        game: object,
    };
};

const onRoundUpdate = (state: IGameContext, object: any) => {
    return {
        ...state,
        round: object,
        response: object.state === RoundState.PRE_LIVE ? null : state.response,
    };
};

const onPlayerResponse = (state: IGameContext, object: any) => {
    return {
        ...state,
        response: object,
    };
};

export const useGameReducer = (state: IGameContext, payload: IPayloadEvent) => {
    const { type, object } = payload;
    switch (type) {
        case BroadcastEvent.WELCOME:
            return onWelcomeHandler(state, object);
        case BroadcastEvent.UPDATE_GAME:
            return onGameUpdate(state, object);
        case BroadcastEvent.UPDATE_ROUND:
            return onRoundUpdate(state, object);
        case BroadcastEvent.PLAYER_ANSWER:
            return onPlayerResponse(state, object);
        default:
            return state;
    }
};
