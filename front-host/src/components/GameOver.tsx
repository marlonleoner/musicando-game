import { useCallback, useContext } from "react";
import { FaCrosshairs, FaCrown, FaStopwatch } from "react-icons/fa6";
import { GameContext } from "../context/GameContext";
import Avatar from "./Avatar";
import ConfettiContainer from "./Confetti";

const GameOver = () => {
    const { players, matchResult } = useContext(GameContext);

    const findPlayer = useCallback(
        (playerId: string) => {
            return players.find((player) => player.id === playerId);
        },
        [players]
    );

    return (
        <div className="w-full h-full flex flex-col items-center mt-8">
            <ConfettiContainer />
            <div className="w-[752px]">
                <div className="h-14 flex items-center justify-center bg-primary rounded-md font-black text-2xl uppercase">
                    Resultado Final
                </div>
                <ul className="flex flex-col gap-2 mt-4 overflow-y-auto">
                    {matchResult.map((result, position) => {
                        const player = findPlayer(result.playerId);
                        const avgTime = (result.totalGuessTime / (1000 * result.correctAnswers)).toFixed(2);

                        return (
                            <li
                                key={player.id}
                                className="w-full h-20 px-8 flex items-center justify-between bg-primary rounded-md uppercase"
                            >
                                <div className="flex items-center">
                                    <span className="pr-4 text-2xl font-black">
                                        {String(position + 1).padStart(2, "0")}
                                    </span>
                                    <Avatar avatar={player.avatar} className="w-14 h-14 rounded-3xl" />
                                    <span className="text-lg font-semibold">{player.name}</span>
                                </div>
                                <div className="flex">
                                    <div className="max-w-32 min-w-32 flex items-baseline tracking-tighter">
                                        <FaStopwatch className="mr-2" size={16} color="#F8D34D" />
                                        <span className="text-2xl font-black">{avgTime}</span>
                                        <span className="text-sm opacity-70">seg</span>
                                    </div>
                                    <div className="max-w-32 min-w-32 flex items-baseline tracking-tighter">
                                        <FaCrosshairs className="mr-2" size={16} color="#F8D34D" />
                                        <span className="text-2xl font-black">{result.correctAnswers}</span>
                                        <span className="text-sm opacity-70">acertos</span>
                                    </div>
                                    <div className="max-w-32 min-w-32 flex items-baseline tracking-tighter">
                                        <FaCrown className="mr-2" size={16} color="#F8D34D" />
                                        <span className="text-2xl font-black">{result.totalPoints}</span>
                                        <span className="text-sm opacity-70">pontos</span>
                                    </div>
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
