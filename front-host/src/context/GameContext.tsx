import { createContext, useEffect, useReducer } from "react";
import { useRoom } from "../hooks/useFetch";
import { useGameReducer } from "../hooks/useGameReducer";
import { useSocket } from "../hooks/useSocket";
import { BroadcastEvent, GameState } from "../types/enums";
import { Element, IGameContext, Reducer } from "../types/types";

const initialGameContext: IGameContext = {
    reconnect: () => {},
    createGame: () => {},
    game: { state: GameState.NOT_CREATED },
    players: [],
    roundResult: [],
    matchResult: [],
};

export const GameContext = createContext<IGameContext>(null as IGameContext);

export const GameProvider = ({ children }: Element) => {
    const [state, dispatch] = useReducer(useGameReducer as Reducer, initialGameContext);
    const { doFetch: createGameCode } = useRoom();

    useEffect(() => {
        const { id, secret } = state.game;
        if (id && secret) {
            localStorage.setItem("msc:reconnect", `${state.game.id}:${state.game.secret}`);
            console.log("msc:reconnect", `${state.game.id}:${state.game.secret}`);
        }
    }, [state.game.id]);

    const onOpenHandle = (evt: Event) => {
        console.log("onOpenHandle", evt);
    };

    const onCloseHandle = () => {
        console.log("WebSocket connection closed");
        dispatch({ type: BroadcastEvent.DISCONNECT });
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
