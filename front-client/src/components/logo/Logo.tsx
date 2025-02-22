import { Element } from "../../types/types";

const Logo = ({ children }: Element) => {
    return (
        <div className="app w-screen h-screen mx-0 overflow-y-hidden flex flex-col">
            <header className="h-16 bg-transparent-dark px-8">
                {/* <BrandedLogo
                    className="flex items-center h-16 space-x-1"
                    svg="w-6 h-6"
                /> */}
                Musicando
            </header>
            <div className="flex-grow relative">{children}</div>
        </div>
    );
};

export default Logo;
