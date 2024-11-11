interface IRoundPreLiveProps {
    round: number;
}

const RoundPreLive = ({ round }: IRoundPreLiveProps) => {
    return (
        <div className="h-full flex items-center justify-center text-center">
            <span className="text-lg tracking-tighter">Prepare-se, a rodada {round} vai começar!</span>
        </div>
    );
};

export default RoundPreLive;
