import { useContext, useMemo } from "react";
import { FaBullseye, FaCrosshairs, FaCrown, FaStopwatch } from "react-icons/fa";
import { GameContext } from "../context/GameContext";
import Avatar from "./Avatar";

const GameOver = () => {
    const { player, match, results, matchResult, resetGame } = useContext(GameContext);

    const points = useMemo(() => (matchResult ? matchResult.totalPoints : 0), [matchResult]);
    const correct = useMemo(() => (matchResult ? matchResult.correctAnswers : 0), [matchResult]);
    const averageTime = useMemo(
        () => (matchResult ? (matchResult.totalGuessTime / (1000 * matchResult.correctAnswers)).toFixed(2) : 0),
        [matchResult]
    );
    const accuracy = useMemo(() => (correct * 100) / match.numberOfSongs, [correct, match]);
    const position = useMemo(() => (matchResult ? matchResult.position : 0), [matchResult]);

    return (
        <div className="w-full h-full flex flex-col items-center justify-between p-4">
            <div className="w-full h-full flex flex-col items-center">
                <div className="w-56 h-56 mt-8 flex items-center justify-center border-4 border-solid rounded-full overflow-hidden border-primary">
                    <Avatar avatar={player.avatar} className="w-56 h-56 rounded-full pt-4" />
                </div>

                <div className="flex flex-col items-center gap-1 mt-2 mb-8">
                    <span className="opacity-90 text-lg">{player.name}</span>
                    <span className="text-xl font-bold tracking-tight uppercase">
                        {matchResult ? `você terminou em ${position}º lugar!` : "você não participou da partida"}
                    </span>
                </div>

                {matchResult && (
                    <div className="w-full flex items-center gap-4 flex-wrap">
                        <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                            <FaCrown className="mx-4" size={32} color="#F8D34D" />
                            <div className="flex flex-col">
                                <strong className="text-lg font-black">{points}</strong>
                                <span className="opacity-80 text-sm">Pontos</span>
                            </div>
                        </div>

                        <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                            <FaCrosshairs className="mx-4" size={32} color="#F8D34D" />
                            <div className="flex flex-col">
                                <strong className="text-lg font-black">{correct}</strong>
                                <span className="opacity-80 text-sm">Acertos</span>
                            </div>
                        </div>

                        <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                            <FaStopwatch className="mx-4" size={32} color="#F8D34D" />
                            <div className="flex flex-col">
                                <strong className="text-lg font-black">{averageTime}s</strong>
                                <span className="opacity-80 text-sm">Tempo Médio</span>
                            </div>
                        </div>

                        <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                            <FaBullseye className="mx-4" size={32} color="#F8D34D" />
                            <div className="flex flex-col">
                                <strong className="text-lg font-black">{accuracy}%</strong>
                                <span className="opacity-80 text-sm">Precisão</span>
                            </div>
                        </div>
                    </div>
                )}
            </div>
            {player?.vip && (
                <button
                    type="button"
                    className="w-full h-16 border-2 border-solid border-primary rounded-lg bg-primary hover:bg-opacity-50 duration-300"
                    onClick={resetGame}
                >
                    Jogar Novamente
                </button>
            )}
        </div>
    );
};

export default GameOver;
