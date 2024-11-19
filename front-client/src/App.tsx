import Controlls from "./components/Controlls";
import GameLayout from "./components/game/GameLayout";
import GameContainer from "./components/GameContainer";
import GameContent from "./components/GameContent";
import GameOver from "./components/GameOver";
import Home from "./components/Home";
import Lobby from "./components/Lobby";
import { GameProvider } from "./context/GameContext";
import { GameFinished, GameInLobby, GameNotCreated, PlayerVip } from "./context/GameFilterContext";
import { ThemeProvider } from "./context/ThemeContext";

function App() {
    return (
        <ThemeProvider>
            <GameProvider>
                <GameContainer>
                    <GameNotCreated fallback={Home}>
                        <GameFinished fallback={GameOver}>
                            <GameContent>
                                <PlayerVip fallback={Controlls}>
                                    <GameInLobby fallback={Lobby}>
                                        <GameLayout />
                                    </GameInLobby>
                                </PlayerVip>
                            </GameContent>
                        </GameFinished>
                    </GameNotCreated>
                </GameContainer>
            </GameProvider>
        </ThemeProvider>
    );
}

export default App;
