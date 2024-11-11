import { useFetch } from "./useFetch";

export const useGameCheck = () => {
    const { doFetch } = useFetch("http://192.168.0.111:7777", "/game");

    const checkCode = async (code: string) => {
        try {
            await doFetch(`/${code}`);
            return true;
        } catch (err) {
            return false;
        }
    };

    return {
        validateCode: async (code: string) => {
            const codeExists = await checkCode(code);
            return codeExists ? null : "jogo não encontrado";
        },
    };
};
