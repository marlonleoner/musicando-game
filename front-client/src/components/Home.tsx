import { FormEvent, useContext, useEffect, useState } from "react";
import { FaRotateRight } from "react-icons/fa6";
import { GameContext } from "../context/GameContext";
import { useAvatar } from "../hooks/useFetch";
import { useGameCheck } from "../hooks/useGameCheck";
import Input from "./lobby/Input";

const Home = () => {
    const { connectClient, reconnectClient } = useContext(GameContext);
    const { validateCode } = useGameCheck();
    const { data: avatar, doFetch: getAvatar, isLoading } = useAvatar();

    const [code, setCode] = useState("");
    const [user, setUser] = useState("");
    const [error, setError] = useState("");
    const [disabled, setDisabled] = useState(true);

    useEffect(() => {
        const gameCode = localStorage.getItem("msc:code");
        const playerId = localStorage.getItem("msc:id");
        const playerSecret = localStorage.getItem("msc:secret");
        if (gameCode && playerId && playerSecret) {
            console.log("reconnecting player to game", gameCode);
            reconnectClient(gameCode, playerId, playerSecret);
        }
    }, []);

    useEffect(() => {
        const checkCode = async () => setError(code.length === 4 ? await validateCode(code) : null);
        checkCode();
    }, [code]);

    useEffect(() => {
        setDisabled(!(code.length === 4 && user && error === null));
    }, [code, user, error]);

    const enterGame = (event: FormEvent) => {
        event.preventDefault();
        connectClient(code, user, avatar);
    };

    const onGameCodeChange = (value: string) => {
        setCode(value);
    };

    const onUserChange = (value: string) => {
        setUser(value);
    };

    const reloadAvatar = () => {
        getAvatar();
    };

    return (
        <form className="flex flex-col items-center text-white mx-4 tracking-tighter" onSubmit={enterGame}>
            <div className="relative w-64 h-64 border-8 border-solid border-purple-800 rounded-full my-6 z-10 flex items-center justify-center">
                {isLoading ? (
                    <FaRotateRight color="black" size={64} />
                ) : (
                    <img src={avatar} alt="Avatar" className="rounded-full w-60 h-60" />
                )}
                <button
                    type="button"
                    className="absolute bottom-2 right-4 bg-purple-800 p-3 rounded-full disabled:opacity-0"
                    onClick={reloadAvatar}
                    disabled={isLoading}
                >
                    <FaRotateRight size={24} />
                </button>
            </div>
            <Input
                className="mb-2"
                label="código da sala"
                placeholder="insira o código"
                max={4}
                tip={error}
                dispatch={onGameCodeChange}
            />
            <Input
                className="mb-2"
                label="nome do jogador"
                placeholder="insira seu nome"
                max={12}
                dispatch={onUserChange}
            />
            <button
                type="submit"
                className="cursor-pointer w-full p-4 mt-2 rounded-md text-lg font-bold uppercase bg-slate-600 disabled:opacity-75"
                disabled={disabled}
            >
                Jogar
            </button>
        </form>
    );
};

export default Home;
