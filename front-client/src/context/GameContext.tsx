import { createContext, useReducer } from "react";
import { useGameReducer } from "../hooks/useGameReducer";
import { useSocket } from "../hooks/useSocket";
import { EventType } from "../types/enums";
import { Element, IGameContext, IPlaylist, Reducer } from "../types/types";

const initialGameContext: IGameContext = {
    connectClient: () => {},
    reconnectClient: () => {},
    changePlaylist: () => {},
    changeRoundDuration: () => {},
    changeNumberOfRounds: () => {},
    startGame: () => {},
    resetGame: () => {},
    sendAnswer: () => {},
};

export const GameContext = createContext<IGameContext>(null as IGameContext);

export const GameProvider = ({ children }: Element) => {
    const [state, dispatch] = useReducer(useGameReducer as Reducer, initialGameContext);

    const onOpenHandle = (evt: Event) => {
        console.log("connected to server");
    };

    const onCloseHandle = () => {
        console.log("connection closed");
    };

    const onMessageHandle = (evt: MessageEvent<any>) => {
        const { type, object } = JSON.parse(evt.data);
        console.log("message received from server", type, object);
        dispatch({ type, object });
    };

    const { connect, sendMessage } = useSocket(onOpenHandle, onCloseHandle, onMessageHandle);

    const connectClient = (code: string, user: string, avatar: string) => {
        connect({
            code,
            username: user,
            avatar,
        });
    };

    const reconnectClient = (code: string, id: string, secret: string) => {
        connect({
            code,
            id,
            secret,
        });
    };

    const changePlaylist = (playlist: IPlaylist) => {
        sendMessage({ type: EventType.CHANGE_PLAYLIST, object: playlist });
    };

    const changeRoundDuration = (duration: number) => {
        sendMessage({ type: EventType.CHANGE_TIMER, object: duration });
    };

    const changeNumberOfRounds = (amount: number) => {
        sendMessage({ type: EventType.CHANGE_AMOUNT, object: amount });
    };

    const startGame = () => {
        const { game } = state;
        sendMessage({
            type: EventType.START_GAME,
            object: {
                timer: game.roundDuration,
                amount: game.numberOfSongs,
                playlist: game.playlist,
            },
        });
    };

    const resetGame = () => {
        sendMessage({
            type: EventType.RESET_GAME,
        });
    };

    const sendAnswer = (answerId: string) => {
        sendMessage({
            type: EventType.SEND_ANSWER,
            object: {
                answerId,
                playerId: state.player.id,
            },
        });
    };

    return (
        <GameContext.Provider
            value={{
                ...state,
                connectClient,
                reconnectClient,
                changePlaylist,
                changeRoundDuration,
                changeNumberOfRounds,
                startGame,
                resetGame,
                sendAnswer,
            }}
        >
            {children}
        </GameContext.Provider>
    );
};
