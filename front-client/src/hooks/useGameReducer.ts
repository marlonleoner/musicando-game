import { BroadcastEvent, MatchState } from "../types/enums";
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
    localStorage.setItem("msc:code", object.id);

    return {
        ...state,
        game: object,
    };
};

const onMatchUpdate = (state: IGameContext, object: any) => {
    return {
        ...state,
        match: object,
        results: object.state === MatchState.PREPARING ? [] : state.results,
        matchResult: object.state === MatchState.PREPARING ? null : state.matchResult,
    };
};

const onMatchResult = (state: IGameContext, object: any) => {
    return {
        ...state,
        matchResult: object,
    };
};

const onRoundUpdate = (state: IGameContext, object: any) => {
    return {
        ...state,
        round: object,
    };
};

const onRoundResult = (state: IGameContext, object: any) => {
    const { results } = state;

    return {
        ...state,
        results: [...results, object],
    };
};

export const useGameReducer = (state: IGameContext, payload: IPayloadEvent) => {
    const { type, object } = payload;
    switch (type) {
        case BroadcastEvent.WELCOME:
            return onWelcomeHandler(state, object);
        case BroadcastEvent.UPDATE_GAME:
            return onGameUpdate(state, object);
        case BroadcastEvent.UPDATE_MATCH:
            return onMatchUpdate(state, object);
        case BroadcastEvent.MATCH_RESULT:
            return onMatchResult(state, object);
        case BroadcastEvent.UPDATE_ROUND:
            return onRoundUpdate(state, object);
        case BroadcastEvent.ROUND_RESULT:
            return onRoundResult(state, object);
        default:
            return state;
    }
};
