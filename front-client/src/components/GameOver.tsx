import { useContext } from "react";
import { FaCrosshairs, FaCrown, FaStopwatch } from "react-icons/fa";
import { GameContext } from "../context/GameContext";

const GameOver = () => {
    const { player, match, matchResult, resetGame } = useContext(GameContext);

    if (!matchResult || !match) return <div className="flex items-center justify-center">Loading...</div>;

    return (
        <div className="w-full h-full flex flex-col items-center justify-between p-4">
            <div className="w-full flex flex-col items-center gap-4">
                <div className="w-8/12 h-20 bg-violet-600 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                    <FaCrown className="mx-8" size={40} color="#F8D34D" />
                    <div className="flex flex-col">
                        <strong className="text-lg font-black">{matchResult.totalPoints}</strong>
                        <span className="opacity-90 text-sm">Pontos</span>
                    </div>
                </div>

                <div className="w-8/12 h-20 bg-violet-600 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                    <FaStopwatch className="mx-8" size={40} color="#F8D34D" />
                    <div className="flex flex-col">
                        <strong className="text-lg font-black">
                            {matchResult.totalGuessTime / (1000 * match.numberOfSongs)}s
                        </strong>
                        <span className="opacity-90 text-sm">Média de Tempo</span>
                    </div>
                </div>

                <div className="w-8/12 h-20 bg-violet-600 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                    <FaCrosshairs className="mx-8" size={40} color="#F8D34D" />
                    <div className="flex flex-col">
                        <strong className="text-lg font-black">{matchResult.correctAnswers}</strong>
                        <span className="opacity-90 text-sm">Acertos</span>
                    </div>
                </div>
            </div>
            {player?.vip && (
                <button
                    type="button"
                    className="w-60 h-12 border-2 border-solid border-primary rounded-lg bg-primary hover:bg-opacity-50 duration-300"
                    onClick={resetGame}
                >
                    Jogar Novamente
                </button>
            )}
        </div>
    );
};

export default GameOver;
