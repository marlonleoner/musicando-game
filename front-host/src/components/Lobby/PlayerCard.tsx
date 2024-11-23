import { FaCrown, FaWifi } from "react-icons/fa6";
import { useAvatar } from "../../hooks/useAvatar";
import { IPlayers } from "../../types/types";

interface PlayerCardProps {
    player: IPlayers;
}

const PlayerCard = ({ player }: PlayerCardProps) => {
    const avatar = useAvatar(player.avatar);

    return (
        <div
            className={`flex items-center gap-3 p-3 rounded-lg transition-all ${
                !player.connected ? "bg-gray-800/30 opacity-60" : "bg-gray-800/50"
            }`}
        >
            <div className="relative">
                <img
                    src={avatar}
                    alt="player avatar"
                    className={`w-10 h-10 rounded-full border-2 transition-all ${
                        !player.connected ? "border-red-400 grayscale" : "border-gray-600"
                    }`}
                />
                {player.vip && (
                    <FaCrown
                        className={`w-4 h-4 absolute -top-2 -left-0 -rotate-45 ${
                            player.connected ? "text-yellow-400" : "text-yellow-400/50"
                        }`}
                    />
                )}
                {!player.connected && (
                    <FaWifi className="w-4 h-4 absolute -bottom-1 -right-1 text-red-400 animate-pulse" />
                )}
            </div>
            <div className="flex-1 min-w-0">
                <p className="truncate font-medium flex items-center gap-1">{player.name}</p>
                <p className={`text-xs ${!player.connected ? "text-red-400" : "text-gray-400"}`}>
                    {!player.connected && "Disconnected"}
                </p>
            </div>
        </div>
    );
};

export default PlayerCard;
