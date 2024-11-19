import Confetti from "react-confetti";
import useWindowSize from "../hooks/useWindowSize";

const ConfettiContainer = () => {
    const [width, height] = useWindowSize();

    return <Confetti width={width} height={height} numberOfPieces={100} wind={0.01} />;
};

export default ConfettiContainer;
