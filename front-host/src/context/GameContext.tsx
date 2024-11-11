import { createContext, useEffect, useReducer } from "react";
import { useRoom } from "../hooks/useFetch";
import { useGameReducer } from "../hooks/useGameReducer";
import { useSocket } from "../hooks/useSocket";
import { GameState } from "../types/enums";
import { Element, IGameContext, Reducer } from "../types/types";

const initialGameContext: IGameContext = {
    reconnect: (code: string, secret: string) => {},
    createGame: () => {},
    game: { state: GameState.NOT_CREATED },
    players: [],
};

export const GameContext = createContext<IGameContext>(null as IGameContext);

export const GameProvider = ({ children }: Element) => {
    const [state, dispatch] = useReducer(useGameReducer as Reducer, initialGameContext);
    const { doFetch: createGameCode } = useRoom();

    useEffect(() => {
        const { code, secret } = state.game;
        if (code && secret) {
            localStorage.setItem("msc:reconnect", `${state.game.code}:${state.game.secret}`);
            console.log("msc:reconnect", `${state.game.code}:${state.game.secret}`);
        }
    }, [state.game.code]);

    const onOpenHandle = (evt: Event) => {
        console.log("onOpenHandle", evt);
    };

    const onCloseHandle = () => {
        localStorage.removeItem("msc:reconnect");
        console.log("WebSocket connection closed");
    };

    const onMessageHandle = (evt: MessageEvent<any>) => {
        const { type, object } = JSON.parse(evt.data);
        console.log("message received from server", type, object);
        dispatch({ type, object });
    };

    const { connect, sendMessage } = useSocket(onOpenHandle, onCloseHandle, onMessageHandle);

    const reconnect = (code: string, secret: string) => {
        connect(code, secret);
    };

    const createGame = async () => {
        const gameCode = await createGameCode();
        connect(gameCode);
    };

    return <GameContext.Provider value={{ ...state, reconnect, createGame }}>{children}</GameContext.Provider>;
};
