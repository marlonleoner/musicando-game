import { useContext, useMemo } from "react";
import { GameState, RoundState } from "../types/enums";
import { FallbackElement, IGameContext } from "../types/types";
import { GameContext } from "./GameContext";

const FilterContext = (
    filter: (context: IGameContext) => boolean,
    { children, fallback: Fallback }: FallbackElement
) => {
    const context = useContext(GameContext);
    const condition = useMemo(() => filter(context), [context]);

    return <>{condition ? <Fallback /> : children}</>;
};

export const GameNotCreated = (elements: FallbackElement) =>
    FilterContext(({ game }: IGameContext) => game.state === GameState.NOT_CREATED, elements);

export const GameFinished = (elements: FallbackElement) =>
    FilterContext(({ game }: IGameContext) => game.state === GameState.FINISHED, elements);

export const GameInLobby = (elements: FallbackElement) =>
    FilterContext(({ game }: IGameContext) => game.state === GameState.LOBBY, elements);

export const RoundInPreLive = (elements: FallbackElement) =>
    FilterContext(
        ({ game, round }: IGameContext) => game.state === GameState.LIVE && round.state === RoundState.PRE_LIVE,
        elements
    );
