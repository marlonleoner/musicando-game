import { useCallback, useContext } from "react";
import { GameContext } from "../context/GameContext";

const GameOver = () => {
    const { players, matchResult } = useContext(GameContext);

    const findResult = useCallback(
        (playerId: string) => {
            return matchResult.find((result) => result.playerId === playerId);
        },
        [players]
    );

    return (
        <div className="w-full h-full flex flex-col items-center mt-8">
            {/* <pre>matchResult: {JSON.stringify(matchResult, null, 4)}</pre>
            <pre>players: {JSON.stringify(players, null, 4)}</pre> */}
            <div className="w-[752px] uppercase">
                <div className="h-14 flex items-center justify-between bg-primary rounded-md">
                    <div className="pl-24">Jogador</div>
                    <div className="w-28 text-center">Pontos</div>
                </div>
                <ul className="flex flex-col gap-2 mt-4 overflow-y-auto">
                    {players.map((player, position) => {
                        const result = findResult(player.id);

                        return (
                            <li
                                key={player.id}
                                className="w-full h-14 flex items-center justify-between bg-primary rounded-md"
                            >
                                <div className="flex items-center">
                                    <span className="px-8 text-2xl font-black">
                                        {String(position + 1).padStart(2, "0")}
                                    </span>
                                    <span className="text-lg font-semibold">{player.name}</span>
                                </div>
                                <div className="w-28 text-center text-2xl font-black">
                                    {result ? result.totalPoints : 0}
                                </div>
                            </li>
                        );
                    })}
                </ul>
            </div>
        </div>
    );
};

export default GameOver;
