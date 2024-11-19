import { motion } from "framer-motion";
import { useContext, useMemo } from "react";
import { GameContext } from "../context/GameContext";
import { RoundState } from "../types/enums";
import { Element } from "../types/types";
import Avatar from "./Avatar";

const GameContent = ({ children }: Element) => {
    const { player, match, round, results } = useContext(GameContext);

    const points = useMemo(() => {
        return results.reduce((accumulator, currentValue) => accumulator + currentValue.points, 0);
    }, [results]);

    return (
        <>
            <div className="w-full h-20 flex items-center justify-between px-4">
                <div className="flex">
                    <div className="rounded-full mr-4">
                        <Avatar avatar={player.avatar} className="w-16 h-16 rounded-full pt-1" />
                    </div>
                    <div className="flex items-center justify-center">
                        <p className="font-bold text-lg">{player.name}</p>
                    </div>
                </div>
                <div className="min-w-24 flex items-center justify-center text-base font-semibold bg-purple-800 rounded-md px-4 py-2">
                    {points}
                </div>
            </div>
            <motion.div
                key="countdown-bar"
                initial={{ width: "100%" }}
                animate={{
                    width: round?.state === RoundState.LIVE ? 0 : "100%",
                    transition: {
                        duration: round?.state === RoundState.LIVE ? match.roundDuration : 0,
                    },
                }}
                className="h-2 bg-purple-800"
            ></motion.div>
            {children}
        </>
    );
};

export default GameContent;
