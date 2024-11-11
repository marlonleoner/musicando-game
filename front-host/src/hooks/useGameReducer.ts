import { BroadcastEvent } from "../types/enums";
import { IGameContext } from "../types/types";

interface IPayloadEvent {
    type: BroadcastEvent;
    object: any;
}

const onWelcomeHandler = (state: IGameContext, { type, object }: IPayloadEvent) => {
    localStorage.setItem("msc:code", object.code);
    localStorage.setItem("msc:secret", object.secret);

    return {
        ...state,
        game: object,
    };
};

export const useGameReducer = (state: IGameContext, payload: IPayloadEvent) => {
    const { type, object } = payload;
    const { game, round, players } = state;
    const index = players.findIndex((p) => p.id === object.id);

    switch (type) {
        case BroadcastEvent.WELCOME:
            return onWelcomeHandler(state, payload);
        case BroadcastEvent.UPDATE_GAME:
            return {
                ...state,
                game: object,
            };
        case BroadcastEvent.UPDATE_ROUND:
            return {
                ...state,
                round: {
                    ...round,
                    ...object,
                },
            };
        case BroadcastEvent.NEW_PLAYER:
            return {
                ...state,
                players: [...players, object],
            };
        case BroadcastEvent.UPDATE_PLAYER:
            return {
                ...state,
                players: [...state.players.slice(0, index), object, ...state.players.slice(index + 1)],
            };
        case BroadcastEvent.REMOVE_PLAYER:
            return {
                ...state,
                players: [...state.players.slice(0, index), ...state.players.slice(index + 1)],
            };
        case BroadcastEvent.RESPONSE_ANSWER:
            // , correctAnswer: true, roundPoints: 180, points: 180}
            return {
                ...state,
                // players: [
                //     ...state.players.slice(0, index),
                //     {
                //         ...player,
                //         points: object.points,
                //         roundPoints: object.roundPoints,
                //     },
                //     ...state.players.slice(index + 1),
                // ],
            };
        //
        //
        //
        // Host Actions
        case BroadcastEvent.CHANGE_PLAYLIST:
            return {
                ...state,
                game: {
                    ...state.game,
                    playlist: object,
                },
            };
        case BroadcastEvent.CHANGE_TIMER:
            return {
                ...state,
                game: {
                    ...state.game,
                    roundDuration: object,
                },
            };
        case BroadcastEvent.CHANGE_AMOUNT:
            return {
                ...state,
                game: {
                    ...state.game,
                    numberOfSongs: object,
                },
            };
        default:
            return state;
    }
};
