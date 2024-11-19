import { IAvatar } from "../types/types";

export const useAvatar = (avatar?: IAvatar, happy?: boolean) => {
    if (!avatar)
        return "https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairTheCaesar&accessoriesType=Blank&hairColor=Brown&facialHairType=Blank&clotheType=ShirtCrewNeck&clotheColor=Gray01&eyeType=Default&eyebrowType=Default&mouthType=Twinkle&skinColor=Brown";

    const { style, accessories, facialHair, top, hairColor, clothe, clotheColor, skinColor } = avatar;

    const mouth = happy === undefined || happy === null ? avatar.mouth : happy ? "Smile" : "Sad";
    const eye = happy === undefined || happy === null ? avatar.eye : happy ? "Happy" : "Cry";
    const eyebrow = happy === undefined || happy === null ? avatar.eyebrow : happy ? "RaisedExcited" : "SadConcerned";

    return `https://avataaars.io/?avatarStyle=${style}&topType=${top}&accessoriesType=${accessories}&hairColor=${hairColor}&facialHairType=${facialHair}&clotheType=${clothe}&clotheColor=${clotheColor}&eyeType=${eye}&eyebrowType=${eyebrow}&mouthType=${mouth}&skinColor=${skinColor}`;
};
