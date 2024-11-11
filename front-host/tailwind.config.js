const plugin = require("tailwindcss");

/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ["./src/**/*.{js,jsx,ts,tsx}"],
    theme: {
        extend: {
            colors: {
                primary: "#44277B",
            },
            textShadow: {
                sm: "2px 1px 2px #FF0000",
                lg: "2px 8px 16px #FF0000",
                DEFAULT:
                    "2px 1px 5px #44277B, -2px 2px 5px #44277B, -3px 3px 5px #44277B, -4px 4px 5px #44277B, -5px 5px 5px #44277B, -6px 6px 5px #44277B, -7px 7px 5px #44277B, -8px 8px 5px #44277B, -9px 9px 5px #44277B, -10px 10px 5px #44277B, -11px 11px 5px #5F3A90, -12px 12px 5px #5F3A90, -13px 13px 5px #5F3A90, -14px 14px 5px #5F3A90, -15px 15px 5px #5F3A90, -16px 16px 5px #5F3A90, -17px 17px 5px #5F3A90, -18px 18px 5px #5F3A90, -19px 19px 5px #5F3A90, -20px 20px 5px #5F3A90, -21px 21px 5px #926CAE, -22px 22px 5px #926CAE, -23px 23px 5px #926CAE, -24px 24px 5px #926CAE, -25px 25px 5px #926CAE, -26px 26px 5px #926CAE, -27px 27px 5px #926CAE, -28px 28px 5px #926CAE, -29px 29px 5px #926CAE, -30px 30px 5px #926CAE",
            },
            keyframes: {
                zoom: {
                    "0%": { transform: "scale(0.9)" },
                    "100%": { transform: "scale(1.25)" },
                },
            },
            animation: {
                zoom: "zoom 16s alternate infinite",
            },
        },
    },
    plugins: [
        plugin(function ({ matchUtilities, theme }) {
            matchUtilities(
                {
                    "text-shadow": (value) => ({
                        textShadow: value,
                    }),
                },
                { values: theme("textShadow") }
            );
        }),
    ],
};
