import { Element } from "../types/types";
import Header from "./Header";

const GameContainer = ({ children }: Element) => {
    return (
        <div className="w-full h-full flex flex-col">
            <Header />
            <div className="w-full min-w-80 max-w-md mx-auto h-full">{children}</div>
        </div>
    );
};

export default GameContainer;
