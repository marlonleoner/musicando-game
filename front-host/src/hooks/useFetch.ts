import axios from "axios";
import { useCallback, useEffect, useState } from "react";

const useFetch = (url: string, path: string, fetchOnMount: boolean = false) => {
    const [fetch] = useState(() => {
        return axios.create({
            baseURL: url,
        });
    });

    const [loading, setLoading] = useState(false);
    const [data, setData] = useState(null);
    const [status, setStatus] = useState(0);

    const doFetch = useCallback(async () => {
        setLoading(true);
        const { data, status } = await fetch.get(path);
        setData(data);
        setLoading(false);
        setStatus(status);

        return data;
    }, [path, fetch]);

    useEffect(() => {
        fetchOnMount && doFetch();
    }, [doFetch, fetchOnMount]);

    return { isLoading: loading, status, data, doFetch };
};

export const useRoom = () => useFetch("http://localhost:7777", "/game");

export const usePlaylist = () => useFetch("http://localhost:8080", "/playlists", true);
