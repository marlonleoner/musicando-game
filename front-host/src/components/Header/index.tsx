import { useContext } from "react";
import { GameContext } from "../../context/GameContext";
import GameCode from "./GameCode";
import NavbarCenter from "./NavbarCenter";
import SmallLogo from "./SmallLogo";

const Header = () => {
    const { game, match, round } = useContext(GameContext);

    return (
        <header className="flex items-center justify-between h-16 px-8 text-[2.4vw] uppercase">
            <SmallLogo />
            <GameCode code={game.id} />
            <NavbarCenter match={match} playlist={match.playlist} round={round} />
        </header>
    );
};

export default Header;
