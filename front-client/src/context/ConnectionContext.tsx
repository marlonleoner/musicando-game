import { useEffect } from "react";
import { Element } from "../types/types";

const ConnectionProvider = ({ children }: Element) => {
    // const { connect, reconnect, disconnect, reconnected } = useContext(GameContext);

    const saveValuesBeforeUnload = () => {
        // console.log("saving reconnection before unmount", room);
        // if (room) {
        //     LocalStorageUtils.setReconnectValues(room.id, room.secret);
        //     disconnect();
        // }
    };

    useEffect(() => {
        // if (!reconnected) {
        //     const [roomId, secret] = LocalStorageUtils.getReconnectValues();
        //     console.log(`trying to reconnect - room: ${roomId} - secret: ${secret}`);
        //     if (roomId && secret) {
        //         connect(roomId, secret);
        //         LocalStorageUtils.removeReconnectValues();
        //     }

        //     reconnect();
        // }

        window.addEventListener("beforeunload", saveValuesBeforeUnload);

        return () => {
            window.removeEventListener("beforeunload", saveValuesBeforeUnload);
        };
    });

    // return <>{reconnected ? children : <p>Loading...</p>}</>;
    return <p>Olá</p>;
};

export default ConnectionProvider;
