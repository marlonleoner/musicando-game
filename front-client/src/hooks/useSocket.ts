import { useCallback, useState } from "react";

interface IConnect {
    code: string;
    username?: string;
    avatar?: string;
    id?: string;
    secret?: string;
}

export const useSocket = (
    onOpen: (evt: Event) => void,
    onClose: () => void,
    onMessage: (evt: MessageEvent<any>) => void
) => {
    const [socket, setSocket] = useState(null as WebSocket);

    const connect = useCallback(
        ({ code, username, avatar, id, secret }: IConnect) => {
            console.log(avatar);

            const q = id && secret ? `id=${id}&secret=${secret}&` : `name=${username}&avatar=${avatar}&`;
            const ws = new WebSocket(`ws://192.168.0.111:7777/musicando/${code}?${q}role=client`);
            ws.addEventListener("open", onOpen);
            ws.addEventListener("close", onClose);
            ws.addEventListener("message", onMessage);
            setSocket(ws);
        },
        [onOpen, onClose, onMessage]
    );

    const disconnect = useCallback(() => {
        if (socket && socket.readyState === socket.OPEN) socket.close(4004);
    }, [socket]);

    const sendMessage = useCallback(
        (args: any) => {
            socket.send(JSON.stringify(args));
        },
        [socket]
    );

    return {
        connect,
        disconnect,
        sendMessage,
        connectionState: socket?.readyState,
    };
};
