package me.marlon.leoner.musicando.manager.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.dto.AvatarDTO;
import me.marlon.leoner.musicando.manager.service.AvatarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService service;

    @GetMapping
    public ResponseEntity<AvatarDTO> getAvatar() {
        return ResponseEntity.ok(service.generateAvatar());
    }
}
