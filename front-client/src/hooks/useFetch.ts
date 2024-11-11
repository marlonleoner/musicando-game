import axios from "axios";
import { useCallback, useEffect, useState } from "react";

export const useFetch = (url: string, path: string, fetchOnMount: boolean = false) => {
    const [fetch] = useState(() => {
        return axios.create({
            baseURL: url,
        });
    });

    const [loading, setLoading] = useState(false);
    const [data, setData] = useState(null);
    const [status, setStatus] = useState(0);

    const doFetch = useCallback(
        async (query?: string) => {
            setLoading(true);
            const response = await fetch.get(path.concat(query ?? ""));
            setData(response.data);
            setLoading(false);
            setStatus(response.status);
            return response;
        },
        [path, fetch]
    );

    useEffect(() => {
        fetchOnMount && doFetch();
    }, [doFetch, fetchOnMount]);

    return { isLoading: loading, status, data, doFetch };
};

export const useRoom = () => useFetch("http://192.168.0.111:7777", "/game");

export const usePlaylist = () => useFetch("http://192.168.0.111:8080", "/playlists", true);

export const useAvatar = () => useFetch("http://192.168.0.111:8080", "/avatar", true);
