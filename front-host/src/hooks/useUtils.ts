export const LocalStorageUtils = {
    setReconnectValues(roomId: string, secret: string) {
        localStorage.setItem("msc:reconnect", `${roomId}:${secret}`);
    },
    getReconnectValues() {
        const values = localStorage.getItem("msc:reconnect")?.split(":") || [];
        return [values[0], values[1]];
    },
    removeReconnectValues() {
        localStorage.removeItem("msc:reconnect");
    },
};
