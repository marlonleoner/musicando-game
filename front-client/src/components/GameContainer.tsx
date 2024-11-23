import { Element } from "../types/types";
import Header from "./Header";

const GameContainer = ({ children }: Element) => {
    return (
        <div className="w-full h-full flex flex-col bg-[#180E2B]">
            <Header />
            <div className="w-full min-w-80 h-full max-w-md mx-auto">{children}</div>
        </div>
    );
};

export default GameContainer;
