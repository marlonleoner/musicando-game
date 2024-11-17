import { AnimatePresence } from "framer-motion";
import { useContext, useEffect, useRef, useState } from "react";
import TickClock from "../../assets/sounds/clock-tick.mp3";
import { GameContext } from "../../context/GameContext";
import { RoundState } from "../../types/enums";
import RoundLive from "../RoundLive";

const GameLayout = () => {
    const { game, match, round } = useContext(GameContext);

    const audioTickClockRef = useRef(null);
    const audioPreviewRef = useRef(null);

    const [tick, setTick] = useState(null);

    useEffect(() => {
        if (tick === 3) audioTickClockRef.current.play();
        if (tick === 1)
            setTimeout(() => {
                audioTickClockRef.current.currentTime = 0;
                audioTickClockRef.current.pause();
            }, 1000);

        setTimeout(() => {
            if (tick > 0) setTick(tick - 1);
        }, 1000);
    }, [tick]);

    useEffect(() => {
        if (!round) return;

        if (round.state === RoundState.PRE_LIVE) {
            audioPreviewRef.current.src = round.preview;
            setTick(3);
        } else if (round.state === RoundState.LIVE) {
            const totalTime = audioPreviewRef.current.duration;
            const startTime = Math.random() * (totalTime - match.roundDuration);
            console.log(
                `song starting in ${startTime}s - total duration: ${totalTime} - round duration: ${match.roundDuration}`
            );
            audioPreviewRef.current.currentTime = startTime;
            audioPreviewRef.current.volume = 0.1;
            audioPreviewRef.current.play();
        } else if (round.state === RoundState.POST_LIVE) {
            fadeoutPreviewSong();
        }
    }, [round]);

    const fadeoutPreviewSong = () => {
        const ratioVolume = audioPreviewRef.current.volume / 50;
        const interval = setInterval(() => {
            const newVolume = audioPreviewRef.current.volume - ratioVolume;
            if (newVolume < 0) {
                audioPreviewRef.current.pause();
                clearInterval(interval);
                return;
            }
            audioPreviewRef.current.volume = newVolume;
        }, 100);
    };

    return (
        <>
            <AnimatePresence>
                <RoundLive alternatives={round?.alternatives} anwserId={round?.answerId} />;
            </AnimatePresence>

            <audio ref={audioTickClockRef}>
                <source src={TickClock} type="audio/mpeg" />
            </audio>

            <audio ref={audioPreviewRef} />
        </>
    );
};

export default GameLayout;
