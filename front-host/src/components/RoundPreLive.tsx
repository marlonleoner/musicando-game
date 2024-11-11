interface IRoundPreLiveProps {
    round: number;
    countdown: number;
}

const RoundPreLive = ({ countdown, round }: IRoundPreLiveProps) => {
    return (
        <div className="flex items-center justify-center flex-col uppercase text-center gap-[4vh]">
            <p className="text-[1.8vw] font-bold">Prepare-se, a rodada {round + 1} vai começar!</p>
            <strong className="font-['Monoton'] text-[6vw] font-normal">{countdown}</strong>
        </div>
    );
};

export default RoundPreLive;
