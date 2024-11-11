import { createContext, useRef, useState } from "react";

interface ISocketContext {
    ready: boolean;
    value: any;
    connect: (roomId: string) => void;
    send: () => void;
}

interface ISocketProviderProps {
    children: React.ReactNode;
}

export const SocketContext = createContext<ISocketContext>({
    ready: false,
    value: null,
    connect: () => {},
    send: () => {},
});

export const SocketProvider = ({ children }: ISocketProviderProps) => {
    const [isReady, setIsReady] = useState(false);
    const [value, setValue] = useState(null);

    const ws = useRef(null);

    const connect = (roomId: string) => {
        const socket = new WebSocket(
            `ws://localhost:7777/musicando/${roomId}?role=host`
        );

        socket.onopen = () => setIsReady(true);
        socket.onclose = () => setIsReady(false);
        socket.onmessage = (event) => setValue(event.data);

        ws.current = socket;

        return () => {
            socket.close();
        };
    };

    const ret = {
        ready: isReady,
        value,
        connect,
        send: ws.current?.send.bind(ws.current),
    };

    return (
        <SocketContext.Provider value={ret}>{children}</SocketContext.Provider>
    );
};
