import { useCallback, useContext, useState } from "react";
import { GameContext } from "../context/GameContext";
import { IAlternative } from "../types/types";
import Alternative from "./Alternative";

interface RoundLiveProps {
    alternatives: IAlternative[];
}

const colors = ["bg-[#FF1178]", "bg-[#FE8700]", "bg-[#037CFE]", "bg-[#00FE2E]"];

const RoundLive = ({ alternatives }: RoundLiveProps) => {
    const { sendAnswer } = useContext(GameContext);

    const [selectedAlternative, setSelectedAlternative] = useState(null);

    const chooseAnswer = useCallback(
        (alternative: IAlternative) => {
            setSelectedAlternative(alternative);
            sendAnswer(alternative.id);
        },
        [sendAnswer]
    );

    if (selectedAlternative) {
        return <Alternative alternative={selectedAlternative} />;
    }

    return (
        <div className="h-full flex flex-col gap-4 p-4">
            {alternatives.map((a, i) => (
                <div
                    key={a.id}
                    aria-hidden="true"
                    className={`w-full h-20 px-2 flex items-center justify-center text-center truncate rounded-md ${colors[i]}`}
                    onClick={() => chooseAnswer(a)}
                >
                    <span className="text-xl font-bold tracking-[-0.08rem]">{a.name}</span>
                </div>
            ))}
        </div>
    );
};

export default RoundLive;
