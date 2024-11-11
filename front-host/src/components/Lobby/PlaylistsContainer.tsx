import { useContext, useEffect, useState } from "react";
import { GameContext } from "../../context/GameContext";
import { usePlaylist } from "../../hooks/useFetch";
import { IPlaylist } from "../../types/types";

const PlaylistsContainer = () => {
    const context = useContext(GameContext);
    const { data }: { data: IPlaylist[] } = usePlaylist();

    const [visiblePlaylists, setVisiblePlaylists] = useState<IPlaylist[]>([]);
    const [selectedPlaylist, setSelectedPlaylist] = useState<IPlaylist>({} as IPlaylist);
    const [selectedIndex, setSelectedIndex] = useState(0);

    const navigate = (index: number) => {
        if (index === 1) return;

        setSelectedIndex((value) => {
            const totalPlaylists = data.length;
            value += index ? 1 : -1;
            if (value === 0) return totalPlaylists - 1;
            if (value === totalPlaylists) return 0;
            return value;
        });

        setVisiblePlaylists(() => {
            const totalPlaylists = data.length;

            if (selectedIndex === 0) {
                return [data[totalPlaylists - 1], ...data.slice(0, 2)];
            }
            if (selectedIndex === totalPlaylists - 1) {
                return [...data.slice(selectedIndex - 1, totalPlaylists), data[0]];
            }

            return data.slice(selectedIndex - 1, selectedIndex + 2);
        });

        setSelectedPlaylist(data[selectedIndex]);
    };

    const isEdgePlaylists = (index: number) => {
        return index === 0 || index === 2;
    };

    const getEdgeTranslate = (index: number) => {
        return index ? "-translate-x-1/3" : "translate-x-1/3";
    };

    useEffect(() => {
        if (!data) return;
        navigate(2);
    }, [data]);

    return selectedPlaylist ? (
        <div className="flex flex-col items-center">
            <div className="flex items-center justify-center">
                {visiblePlaylists.map((p: any, i: number) => (
                    <div
                        key={p.id}
                        onClick={() => navigate(i)}
                        role="presentation"
                        className={
                            isEdgePlaylists(i)
                                ? `z-0 cursor-pointer scale-75 opacity-40 ${getEdgeTranslate(i)}`
                                : "z-10"
                        }
                    >
                        <img
                            className="w-20 h-2w-20 border-sm object-cover"
                            src={p.thumbnail}
                            alt={`Thumbnail '${p.name}' ${i}`}
                        />
                    </div>
                ))}
            </div>

            <div className="mt-[2vh] bg-white rounded-[0.4vw] min-w-[20vw] px-[1vw] py-[1vw]">
                <div className="flex mb-[0.5vw]">
                    <div className="mt-[0.35vw] mr-[0.5vw]">
                        <svg
                            width="20"
                            height="20"
                            fill="#44277B"
                            stroke="#44277B"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth="2"
                            className="feather feather-play"
                            viewBox="0 0 24 24"
                        >
                            <defs />
                            <path d="M5 3l14 9-14 9V3z" />
                        </svg>
                    </div>

                    <div className="flex flex-col justify-center text-primary">
                        <strong className="text-[1.05vw] tracking-tighter">{selectedPlaylist?.name}</strong>
                        <span className="text-[0.85vw] tracking-tighter">{selectedPlaylist?.total_songs} músicas</span>
                    </div>
                </div>

                <div className="w-full h-[0.5vh] bg-primary rounded overflow-hidden">
                    <span className="room_warmup_player_bar_progress"></span>
                </div>
            </div>
        </div>
    ) : (
        <p>Loading..</p>
    );
};

export default PlaylistsContainer;
