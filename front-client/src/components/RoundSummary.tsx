import { useMemo } from "react";
import { FaCrown, FaStopwatch } from "react-icons/fa6";
import { IPlayer, IRoundResult } from "../types/types";
import Avatar from "./Avatar";

interface RoundSummaryProps {
    player: IPlayer;
    result?: IRoundResult;
}

const RoundSummary = ({ player, result }: RoundSummaryProps) => {
    const summaryLabel = useMemo(() => {
        if (result?.correct === null || result?.correct === undefined) {
            return "";
        }

        return "você " + (result?.correct ? "acertou" : "errou") + "!";
    }, [result?.correct]);

    const summaryBorder = useMemo(() => {
        if (result?.correct === null || result?.correct === undefined) {
            return "border-[#180E2B]";
        }

        return result?.correct ? "border-[#15803d]" : "border-[#b91c1c]";
    }, [result?.correct]);

    if (!result) return <div>Loading...</div>;

    return (
        <div className="w-full h-full flex flex-col items-center">
            <div
                className={`w-56 h-56 mt-8 flex items-center justify-center border-8 border-solid rounded-full overflow-hidden ${summaryBorder}`}
            >
                <Avatar avatar={player.avatar} happy={result.correct} className="w-56 h-56 rounded-full pt-4" />
            </div>

            <div className="flex flex-col items-center gap-1 mt-2 mb-8">
                <span className="opacity-90 text-lg">{player.name}</span>
                <span className="text-xl font-bold tracking-tight uppercase">{summaryLabel}</span>
            </div>

            <div className="w-full flex items-center gap-4 flex-wrap px-4">
                <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                    <FaCrown className="mx-4" size={32} color="#F8D34D" />
                    <div className="flex flex-col">
                        <strong className="text-xl font-black">+ {result.points}</strong>
                        <span className="opacity-80 text-sm">Pontos</span>
                    </div>
                </div>

                <div className="[flex:1_0_21%] h-20 border-2 border-purple-300 border-solid rounded-lg flex items-center">
                    <FaStopwatch className="mx-4" size={32} color="#F8D34D" />
                    <div className="flex flex-col">
                        <strong className="text-lg font-black">{result.guessTime / 1000}s</strong>
                        <span className="opacity-80 text-sm">Tempo</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RoundSummary;
