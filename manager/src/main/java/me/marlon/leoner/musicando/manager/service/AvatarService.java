package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.avatar.*;
import me.marlon.leoner.musicando.manager.domain.dto.AvatarDTO;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final Random random;

    public AvatarDTO generateAvatar() {
        AvatarTopEnum top = AvatarTopEnum.getRandom(random);
        AvatarHairColorEnum hairColor = AvatarHairColorEnum.getRandom(random);
        AvatarClotheEnum clothe = AvatarClotheEnum.getRandom(random);
        AvatarClotheColorEnum clotheColor = AvatarClotheColorEnum.getRandom(random);
        AvatarSkinEnum skin = AvatarSkinEnum.getRandom(random);

        return new AvatarDTO(top.getValue(), hairColor.getValue(), clothe.getValue(), clotheColor.getValue(), skin.getValue());
    }
}
