import { useContext, useMemo } from "react";
import { GameContext } from "../../context/GameContext";

const Lobby = () => {
    const { game, players } = useContext(GameContext);

    const avatar = useMemo(
        () => (
            <img
                className="w-24 h-24 rounded-full"
                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairTheCaesar&accessoriesType=Blank&hairColor=Brown&facialHairType=Blank&clotheType=ShirtCrewNeck&clotheColor=Gray01&eyeType=Default&eyebrowType=Default&mouthType=Twinkle&skinColor=Brown"
                alt="Avatar"
            />
        ),
        []
    );

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
                                    {avatar}
                                </div>
                                <span className="text-xs">{player.name}</span>
                            </>
                        ) : (
                            <div className="w-24 h-24 flex items-center justify-center opacity-30">{avatar}</div>
                        )}
                    </div>
                ))}
            </div>
            <div className="w-full p-8 rounded-xl flex flex-col gap-8 bg-primary uppercase tracking-tighter font-bold text-xl">
                <div className="flex justify-between pr-4 py-8 border-b border-solid">
                    <span>Número de músicas:</span>
                    <span>{game.numberOfSongs} Músicas</span>
                </div>
                <div className="flex justify-between pr-4 py-8 border-b border-solid">
                    <span>Duração da rodada:</span>
                    <span>{game.roundDuration} segundos</span>
                </div>
                <div className="flex items-center gap-4">
                    <img className="w-36 h-36 rounded-2xl" src={game.playlist?.thumbnail} alt="playlist thumbnail" />
                    <div className="flex flex-col">
                        <span>{game.playlist?.name}</span>
                        <span className="font-normal text-gray-300">{game.playlist?.total_songs} músicas</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Lobby;
