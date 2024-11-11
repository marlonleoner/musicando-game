import { Element } from "../types/types";
import Header from "./Header";

const GameContainer = ({ children }: Element) => {
    return (
        <div className="z-10 w-full h-full flex flex-col">
            <Header />
            <div className="w-full h-full overflow-hidden mt-6">{children}</div>
        </div>
    );
};

export default GameContainer;
