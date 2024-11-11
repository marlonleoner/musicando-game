import { motion } from "framer-motion";
import { IAlternative } from "../types/types";

interface IRoundLiveProps {
    anwserId?: string;
    alternatives: IAlternative[];
}

const RoundLive = ({ alternatives, anwserId }: IRoundLiveProps) => {
    return (
        <>
            <motion.div
                key={"live-header"}
                initial="hidden"
                animate={{ opacity: 1, transform: "scale(1)" }}
                exit={{ opacity: 0, transform: "scale(0)" }}
                transition={{ duration: 0.3 }}
                className="text-2xl font-bold uppercase tracking-tighter"
            >
                adivinhe a música!
            </motion.div>
            <motion.div
                key={"alternatives-container"}
                initial="hidden"
                animate={{ opacity: 1, display: "flex", marginTop: "24px" }}
                exit={{ opacity: 0, display: "none" }}
                transition={{ delay: 2, duration: 0.3 }}
                className="max-w-4xl flex flex-wrap justify-between gap-2 text-xl font-black uppercase tracking-tighter"
            >
                {alternatives?.map((a, i) => (
                    <motion.div
                        key={a.id}
                        initial={{ x: i % 2 ? "1000%" : "-1000%" }}
                        animate={{ x: 0 }}
                        exit={{ x: i % 2 ? "1000%" : "-1000%" }}
                        transition={{ delay: 2 + i * 0.25, duration: 0.3 }}
                        className={`flex-[1_0_49%] px-16 py-8 bg-white text-primary text-center truncate rounded-md border-2 border-red-900 ${
                            a.id === anwserId ? "bg-green-300 text-green-950 transition-all duration-300" : ""
                        }`}
                    >
                        {a.name}
                    </motion.div>
                ))}
            </motion.div>
        </>
    );
};

export default RoundLive;
