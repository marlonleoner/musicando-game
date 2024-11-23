import { useCallback, useState } from "react";

export const useSocket = (
    onOpen: (evt: Event) => void,
    onClose: () => void,
    onMessage: (evt: MessageEvent<any>) => void
) => {
    const [socket, setSocket] = useState(null as WebSocket);

    const connect = useCallback(
        (roomId: string, secret?: string) => {
            const q = secret ? `id=0&secret=${secret}&` : "";
            const ws = new WebSocket(`ws://192.168.0.111:7777/musicando/${roomId}?${q}role=host`);
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
