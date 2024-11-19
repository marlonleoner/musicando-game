import { useContext, useMemo } from "react";
import { GameContext } from "../../context/GameContext";
import { RoundState } from "../../types/enums";
import Alternative from "../Alternative";
import RoundLive from "../RoundLive";
import RoundPreLive from "../RoundPreLive";
import RoundSummary from "../RoundSummary";

const GameLayout = () => {
    const { player, round, results } = useContext(GameContext);

    const roundResult = useMemo(() => {
        if (!results) return null;
        return results.find((r) => r.roundId === round.id);
    }, [round, results]);

    return (
        <>
            {(() => {
                switch (round?.state) {
                    case RoundState.PRE_LIVE:
                        return <RoundPreLive round={round.roundNumber} />;
                    case RoundState.LIVE:
                        return <RoundLive alternatives={round.alternatives} />;
                    case RoundState.FINISHED:
                        return <Alternative alternative={round.answer} correct={roundResult?.correct} />;
                    case RoundState.SUMMARY:
                        return <RoundSummary player={player} result={roundResult} />;
                    default:
                        return <p>State não mapeado</p>;
                }
            })()}
        </>
    );
};

export default GameLayout;
