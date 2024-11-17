import { useContext } from "react";
import { GameContext } from "../context/GameContext";
import { Element } from "../types/types";
import Header from "./Header";

const GameContainer = ({ children }: Element) => {
    const { game, match } = useContext(GameContext);

    if (!game || !match) return <div>Loading...</div>;

    return (
        <div className="z-10 w-full h-full flex flex-col">
            <Header />
            <div className="w-full h-full overflow-hidden mt-6">{children}</div>
        </div>
    );
};

export default GameContainer;
