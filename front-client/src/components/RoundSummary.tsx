import { IAlternative, IRoundResponse } from "../types/types";

interface RoundSummaryProps {
    answer: IAlternative;
    response: IRoundResponse;
}

const RoundSummary = ({ answer, response }: RoundSummaryProps) => {
    if (!response) {
        return <div>Loading..</div>;
    }

    return (
        // <div
        //     className={`flex flex-col items-center p-4 tracking-[-0.1rem] ${
        //         response.correctAnswer ? "bg-green-400" : "bg-red-400"
        //     }`}
        // >
        //     <span>Você {response.correctAnswer ? "Acertou" : "Errou"}</span>
        //     <div className="w-64 h-64 mt-4 mb-2">
        //         <img className="rounded-xl" src={answer.thumbnail} alt="thumbnail answer" />
        //     </div>
        //     <span className="font-black text-xl">{answer.name}</span>
        //     <span className="font-bold text-lg opacity-75">{answer.artist}</span>
        // </div>
        <div>Summary</div>
    );
};

export default RoundSummary;
