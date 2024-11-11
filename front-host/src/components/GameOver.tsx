const GameOver = () => {
    return (
        <div className="w-full h-full flex flex-col items-center">
            <div className="flex-1">
                <div>Lista de Jogadores</div>
                <ul>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                    <li>Player 1</li>
                </ul>
            </div>
            {/* <div className="flex gap-8 mb-8">
                <button
                    type="button"
                    className="w-60 h-12 border-2 border-solid border-white rounded-lg hover:bg-white hover:bg-opacity-10 duration-300"
                >
                    Sair
                </button>
                <button
                    type="button"
                    className="w-60 h-12 border-2 border-solid border-primary rounded-lg bg-primary hover:bg-opacity-50 duration-300"
                    onClick={resetGame}
                >
                    Jogar Novamente
                </button>
            </div> */}
        </div>
    );
};

export default GameOver;
