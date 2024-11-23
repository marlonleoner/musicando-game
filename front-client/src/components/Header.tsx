import { useContext } from "react";
import { FaRightFromBracket } from "react-icons/fa6";
import { GameContext } from "../context/GameContext";

const Header = () => {
    const { disconnectClient, player } = useContext(GameContext);

    return (
        <header className="relative flex items-center justify-center h-16 px-8 font-['Monoton'] text-3xl text-center">
            <span className="flex-1 tracking-tighter [text-shadow:2px_1px_5px_#44277B,-2px_2px_5px_#44277B,-3px_3px_5px_#44277B,-4px_4px_5px_#5F3A90,-5px_5px_5px_#5F3A90,-9px_6px_5px_#5F3A90,-7px_7px_5px_#926CAE,-8px_8px_5px_#926CAE,-9px_9px_5px_#926CAE]">
                Musicando
            </span>
            {player && (
                <button className="h-full px-6 absolute right-0" onClick={disconnectClient}>
                    <FaRightFromBracket size={20} />
                </button>
            )}
        </header>
    );
};

export default Header;
