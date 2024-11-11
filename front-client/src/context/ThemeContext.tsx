import { motion } from "framer-motion";
import { Element } from "../types/types";

const colors = [
    "#A52A2A", // Brown
    "#7FFFD4", // Aquamarine
    "#D2691E", // Chocolate
    "#FF4500", // OrangeRed
    "#8A2BE2", // BlueViolet
];

export const ThemeProvider = ({ children }: Element) => {
    return (
        <motion.div
            // initial={{ backgroundColor: colors[0] }}
            // animate={{ backgroundColor: colors }}
            // transition={{
            //     duration: 10,
            //     ease: "easeInOut",
            //     times: [0, 0.3, 0.5, 0.7, 0.9],
            //     loop: Infinity,
            //     repeatDelay: 1,
            // }}
            className="w-screen h-screen flex items-center justify-center text-white bg-black"
        >
            {children}
        </motion.div>
    );
};
