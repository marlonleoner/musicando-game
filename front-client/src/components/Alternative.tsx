import { motion } from "framer-motion";
import { useMemo } from "react";
import { IAlternative } from "../types/types";

interface AlternativeProps {
    alternative: IAlternative;
    correct?: boolean;
}

const Alternative = ({ alternative, correct }: AlternativeProps) => {
    const label = useMemo(() => {
        if (correct === null || correct === undefined) {
            return "Selecionou";
        }

        return correct ? "Acertou" : "Errou";
    }, [correct]);

    const bg = useMemo(() => {
        if (correct === null || correct === undefined) {
            return "#180E2B";
        }

        return correct ? "#15803d" : "#b91c1c";
    }, [correct]);

    return (
        <motion.div
            className={`h-full flex flex-col items-center p-4 tracking-[-0.1rem] ${bg}`}
            animate={{
                backgroundColor: bg,
            }}
        >
            <span className="text-lg">Você {label}</span>
            <div className="w-64 h-64 mt-8 mb-2">
                <img className="rounded-xl" src={alternative.thumbnail} alt="thumbnail selected alternative" />
            </div>
            <span className="font-black text-xl">{alternative.name}</span>
            <span className="font-bold text-lg opacity-75">{alternative.artist}</span>
        </motion.div>
    );
};

export default Alternative;
