import { useAvatar } from "../hooks/useAvatar";
import { IAvatar } from "../types/types";

interface AvatarProps {
    avatar?: IAvatar;
    className?: string;
}

const Avatar = ({ avatar, className }: AvatarProps) => {
    const avatarUrl = useAvatar(avatar);

    return <img src={avatarUrl} className={className} alt="Avatar" />;
};

export default Avatar;
