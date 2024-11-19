import { useAvatar } from "../hooks/useAvatar";
import { IAvatar } from "../types/types";

interface AvatarProps {
    avatar?: IAvatar;
    happy?: boolean;
    className?: string;
}

const Avatar = ({ avatar, happy, className }: AvatarProps) => {
    const avatarUrl = useAvatar(avatar, happy);

    return <img className={className} src={avatarUrl} alt="Avatar" />;
};

export default Avatar;
