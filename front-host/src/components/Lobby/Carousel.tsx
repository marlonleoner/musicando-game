import { useEffect, useState } from "react";
import { FaMinus, FaPlus } from "react-icons/fa6";

interface ICarousel {
    label: string;
    elements: any[];
    method: (value: number) => void;
}

const Carousel = ({ label, elements, method }: ICarousel) => {
    const [visible, setVisible] = useState(0);

    const isOnLimits = (limit: number) => {
        return visible === limit;
    };

    const get = (value: number) => {
        if (value < 0) return 0;
        if (value >= elements.length) return elements.length - 1;
        return value;
    };

    const increment = () => {
        setVisible((prev) => get(prev + 1));
    };

    const decrement = () => {
        setVisible((prev) => get(prev - 1));
    };

    useEffect(() => {
        method(elements[visible]);
    }, [visible]);

    return (
        <div className="flex flex-col justify-center items-center">
            <button
                type="button"
                className="text-white disabled:text-gray-600 bg-[#0000006F] rounded-t-lg"
                disabled={isOnLimits(elements.length - 1)}
                onClick={increment}
            >
                <FaPlus />
            </button>
            <div>
                <p>
                    {elements[visible]} {label}
                </p>
            </div>
            <button
                type="button"
                className="text-white disabled:text-gray-600 bg-[#0000006F] rounded-b-lg"
                disabled={isOnLimits(0)}
                onClick={decrement}
            >
                <FaMinus />
            </button>
        </div>
    );
};

export default Carousel;
