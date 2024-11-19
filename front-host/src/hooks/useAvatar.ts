import { IAvatar } from "../types/types";

export const useAvatar = (avatar?: IAvatar) => {
    if (!avatar)
        return "https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairTheCaesar&accessoriesType=Blank&hairColor=Brown&facialHairType=Blank&clotheType=ShirtCrewNeck&clotheColor=Gray01&eyeType=Default&eyebrowType=Default&mouthType=Twinkle&skinColor=Brown";

    const { style, accessories, facialHair, eye, eyebrow, mouth, top, hairColor, clothe, clotheColor, skinColor } =
        avatar;

    return `https://avataaars.io/?avatarStyle=${style}&topType=${top}&accessoriesType=${accessories}&hairColor=${hairColor}&facialHairType=${facialHair}&clotheType=${clothe}&clotheColor=${clotheColor}&eyeType=${eye}&eyebrowType=${eyebrow}&mouthType=${mouth}&skinColor=${skinColor}`;
};
