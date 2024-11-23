import { useContext, useEffect, useMemo, useState } from "react";
import { FaCheck, FaSquareMinus, FaSquarePlus } from "react-icons/fa6";
import { GameContext } from "../../context/GameContext";
import { usePlaylist } from "../../hooks/useFetch";
import { IPlaylist } from "../../types/types";

const Controlls = () => {
    const { changeNumberOfRounds, changeRoundDuration, changePlaylist, startGame } = useContext(GameContext);

    const { data }: { data: IPlaylist[] } = usePlaylist();

    const numberOfSongsList = useMemo(() => [2, 5, 10, 15, 20, 35, 50], []);
    const roundDurationList = useMemo(() => [3, 5, 10, 15, 20, 30], []);

    const [numberOfSongs, setNumberOfSongs] = useState(0);
    const [roundDuration, setRoundDuration] = useState(0);
    const [selectedPlaylist, setSelectedPlaylist] = useState(null);

    useEffect(() => {
        if (data) setSelectedPlaylist(data[0]);
    }, [data]);

    useEffect(() => {
        changeNumberOfRounds(numberOfSongsList[numberOfSongs]);
    }, [numberOfSongs]);

    useEffect(() => {
        changeRoundDuration(roundDurationList[roundDuration]);
    }, [roundDuration]);

    useEffect(() => {
        changePlaylist(selectedPlaylist);
    }, [selectedPlaylist]);

    const changeAmount = (delta: number) => {
        setNumberOfSongs((prev) => Math.max(0, Math.min(prev + delta, numberOfSongsList.length - 1)));
    };

    const changeTimer = (delta: number) => {
        setRoundDuration((prev) => Math.max(0, Math.min(prev + delta, roundDurationList.length - 1)));
    };

    const changePlaylistId = (playlist: IPlaylist) => {
        setSelectedPlaylist(playlist);
    };

    return (
        <div className="grow flex flex-col gap-4 mx-2 my-3 p-4 bg-violet-800 bg-opacity-75 rounded-lg text-base">
            <div className="w-full pb-4 flex justify-between text-lg font-bold border-b-2 border-solid border-gray-300">
                <span className="uppercase">número de músicas</span>
                <div className="flex items-center gap-4">
                    <button
                        type="button"
                        onClick={() => changeAmount(-1)}
                        className="rounded-full disabled:opacity-50 transition-all duration-200"
                        disabled={numberOfSongs === 0}
                    >
                        <FaSquareMinus size={24} />
                    </button>
                    <span className="flex-1">{String(numberOfSongsList[numberOfSongs]).padStart(2, "0")}</span>
                    <button
                        type="button"
                        onClick={() => changeAmount(1)}
                        className="rounded-full disabled:opacity-50 transition-all duration-200"
                        disabled={numberOfSongs === numberOfSongsList.length - 1}
                    >
                        <FaSquarePlus size={24} />
                    </button>
                </div>
            </div>
            <div className="w-full pb-4 flex justify-between text-lg font-bold border-b-2 border-solid border-gray-300">
                <span className="uppercase">tempo da rodada</span>
                <div className="flex items-center gap-4">
                    <button
                        type="button"
                        onClick={() => changeTimer(-1)}
                        className="rounded-full disabled:opacity-50 transition-all duration-200"
                        disabled={roundDuration === 0}
                    >
                        <FaSquareMinus size={24} />
                    </button>
                    <span className="flex-1">{String(roundDurationList[roundDuration]).padStart(2, "0")}</span>
                    <button
                        type="button"
                        onClick={() => changeTimer(1)}
                        className="rounded-full disabled:opacity-50 transition-all duration-200"
                        disabled={roundDuration === roundDurationList.length - 1}
                    >
                        <FaSquarePlus size={24} />
                    </button>
                </div>
            </div>
            <div className="w-full h-80 overflow-y-auto scroll-smooth [scrollbar-width:none] flex flex-col gap-4">
                {data?.map((playlist) => (
                    <div
                        aria-hidden="true"
                        key={playlist.id}
                        className="w-full flex items-center space-x-4"
                        onClick={() => changePlaylistId(playlist)}
                    >
                        <div className="w-24 h-24 flex-shrink-0 relative">
                            <img
                                className="w-full h-full rounded-md object-cover"
                                src={playlist.thumbnail}
                                alt={`Thumbnail '${playlist.name}' ${playlist.id}`}
                            />
                            {selectedPlaylist?.id === playlist.id && (
                                <div className="w-full h-full rounded-md bg-black bg-opacity-45 absolute top-0 left-0 flex items-center justify-center border-solid border-2">
                                    <FaCheck size={30} />
                                </div>
                            )}
                        </div>
                        <div className="max-w-xs h-full flex flex-col font-bold tracking-tighter overflow-y-hidden">
                            <span
                                className={`text-xl capitalize ${
                                    selectedPlaylist?.id !== playlist.id && "opacity-90"
                                } break-words line-clamp-2`}
                            >
                                {playlist.name}
                            </span>
                            <span className="text-sm opacity-80">{playlist.total_songs} músicas</span>
                        </div>
                    </div>
                ))}
            </div>
            <button
                className="bg-gold-500 text-violet-800 text-xl font-bold px-8 py-2 rounded-md uppercase border-4 border-double border-transparent hover:border-white"
                onClick={startGame}
            >
                <span>Começar</span>
            </button>
        </div>
    );
};

export default Controlls;
