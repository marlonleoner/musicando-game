import GameLayout from "./components/Game/GameLayout";
import GameContainer from "./components/GameContainer";
import GameOver from "./components/GameOver";
import Home from "./components/Home";
import Lobby from "./components/Lobby";
import { GameProvider } from "./context/GameContext";
import { GameFinished, GameInLobby, GameNotCreated } from "./context/GameFilterContext";
import { ThemeProvider } from "./context/ThemeContext";

function App() {
    return (
        <ThemeProvider>
            <GameProvider>
                <GameNotCreated fallback={Home}>
                    <GameContainer>
                        <GameFinished fallback={GameOver}>
                            <GameInLobby fallback={Lobby}>
                                <GameLayout />
                            </GameInLobby>
                        </GameFinished>
                    </GameContainer>
                </GameNotCreated>
            </GameProvider>
        </ThemeProvider>
    );
}

export default App;
