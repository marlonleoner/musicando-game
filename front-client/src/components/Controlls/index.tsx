import { useContext, useEffect, useMemo, useState } from "react";
import { FaCheck, FaCircleMinus, FaCirclePlus } from "react-icons/fa6";
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
        <div className="flex flex-col gap-4 mx-2 my-3 p-4 bg-violet-800 bg-opacity-75 rounded-lg text-base">
            <div className="w-full pb-4 flex justify-between uppercase font-bold border-b-2 border-solid border-gray-300">
                <span>número de músicas</span>
                <div className="flex items-center gap-2">
                    <button type="button" onClick={() => changeAmount(-1)} className="rounded-full">
                        <FaCircleMinus size={16} />
                    </button>
                    <span className="flex-1">{numberOfSongsList[numberOfSongs]}</span>
                    <button type="button" onClick={() => changeAmount(1)} className="rounded-full">
                        <FaCirclePlus size={16} />
                    </button>
                </div>
            </div>
            <div className="w-full pb-4 flex justify-between uppercase font-bold border-b-2 border-solid border-gray-300">
                <span>tempo da rodada</span>
                <div className="flex items-center gap-2">
                    <button type="button" className="rounded-full" onClick={() => changeTimer(-1)}>
                        <FaCircleMinus size={16} />
                    </button>
                    <span className="flex-1">{roundDurationList[roundDuration]}</span>
                    <button type="button" className="rounded-full" onClick={() => changeTimer(1)}>
                        <FaCirclePlus size={16} />
                    </button>
                </div>
            </div>
            <div className="overflow-x-auto scroll-smooth [scrollbar-width:none] flex gap-4 w-full">
                {data?.map((playlist) => (
                    <div
                        aria-hidden="true"
                        key={playlist.id}
                        className="inline-flex flex-col relative w-32"
                        onClick={() => changePlaylistId(playlist)}
                    >
                        <div className="relative w-32 h-32">
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
                        <div className="w-full truncate">
                            <span
                                className={`text-sm font-bold tracking-[-0.1rem] capitalize ${
                                    selectedPlaylist?.id !== playlist.id && "opacity-75"
                                }`}
                            >
                                {playlist.name}
                            </span>
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
