import { ChangeEvent, useState } from "react";

interface InputProps {
    className?: string;
    label: string;
    placeholder: string;
    max: number;
    tip?: string;
    dispatch: (value: string) => void;
}

const Input = ({ className, label, placeholder, max, tip, dispatch }: InputProps) => {
    const [value, setValue] = useState("");

    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
        const v = event.target.value.toUpperCase();
        if (v.length <= max) {
            setValue(v);
            dispatch(v);
        }
    };

    return (
        <div className={`w-full flex flex-col uppercase font-bold ${className ?? ""}`}>
            <div className="flex items-center justify-between">
                <label className="text-lg mb-1">{label}</label>
                {tip && <span className="text-sm font-semibold text-gray-500">{tip}</span>}
            </div>
            <input
                type="text"
                className="h-8 mb-4 px-4 py-6 text-black rounded-md text-lg placeholder:text-[#404040] placeholder:uppercase placeholder:text-base"
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            />
        </div>
    );
};

export default Input;
