package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.avatar.*;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private static final String BASE_URL = "https://avataaars.io/?avatarStyle=Transparent&accessoriesType=Blank&facialHairType=Blank&eyeType=Default&eyebrowType=Default&mouthType=Twinkle&topType=%s&hairColor=%s&clotheType=%s&clotheColor=%s&skinColor=%s";

    private final Random random;

    public String generateAvatar() {
        AvatarTopEnum top = AvatarTopEnum.getRandom(random);
        AvatarHairColorEnum hairColor = AvatarHairColorEnum.getRandom(random);
        AvatarClotheEnum clothe = AvatarClotheEnum.getRandom(random);
        AvatarClotheColorEnum clotheColor = AvatarClotheColorEnum.getRandom(random);
        AvatarSkinEnum skin = AvatarSkinEnum.getRandom(random);

        return String.format(BASE_URL, top.getValue(), hairColor.getValue(), clothe.getValue(), clotheColor.getValue(), skin.getValue());
    }
}
