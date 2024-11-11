import { useContext } from "react";
import { GameContext } from "../context/GameContext";

const GameOver = () => {
    const { player, resetGame } = useContext(GameContext);

    return (
        <div className="h-full flex flex-col items-center justify-between p-4">
            <div>Game Acabou</div>
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
