import { useContext } from "react";
import { GameContext } from "../../context/GameContext";
import Avatar from "../Avatar";

const Lobby = () => {
    const { match, players } = useContext(GameContext);

    return (
        <div className="max-w-4xl h-full m-auto flex flex-col items-center justify-center gap-8">
            <div className="h-fit flex flex-wrap gap-4">
                {players.map((player) => (
                    <div key={player.id} className="h-32 w-24 text-center truncate ">
                        {player.name ? (
                            <>
                                <div
                                    className={`w-24 h-24 flex items-center justify-center border-4 border-solid rounded-full overflow-hidden border-primary
                                        ${player.vip && "border-yellow-500"}
                                        ${!player.connected && "opacity-35"}`}
                                >
                                    <Avatar avatar={player.avatar} className="w-24 h-24 rounded-full" />
                                </div>
                                <span className="text-xs">{player.name}</span>
                            </>
                        ) : (
                            <div className="w-24 h-24 flex items-center justify-center opacity-30">
                                <Avatar className="w-24 h-24 rounded-full" />
                            </div>
                        )}
                    </div>
                ))}
            </div>
            <div className="w-full p-8 rounded-xl flex flex-col gap-8 bg-primary uppercase tracking-tighter font-bold text-xl">
                <div className="flex justify-between pr-4 py-8 border-b border-solid">
                    <span>Número de músicas:</span>
                    <span>{match.numberOfSongs} Músicas</span>
                </div>
                <div className="flex justify-between pr-4 py-8 border-b border-solid">
                    <span>Duração da rodada:</span>
                    <span>{match.roundDuration} segundos</span>
                </div>
                <div className="flex items-center gap-4">
                    <img className="w-36 h-36 rounded-2xl" src={match.playlist?.thumbnail} alt="playlist thumbnail" />
                    <div className="flex flex-col">
                        <span>{match.playlist?.name}</span>
                        <span className="font-normal text-gray-300">{match.playlist?.total_songs} músicas</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Lobby;
