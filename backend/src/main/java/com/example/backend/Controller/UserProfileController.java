package com.example.backend.Controller;

import com.example.backend.DTO.AvatarDto;
import com.example.backend.DTO.CreateUserProfileDto;
import com.example.backend.DTO.UserProfileDto;
import com.example.backend.Mapper.AvatarMapper;
import com.example.backend.Service.AvatarService;
import com.example.backend.Service.UserProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/userProfile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService service;
    private final AvatarService avatarService;
    private final AvatarMapper avatarMapper;

    @GetMapping
    public ResponseEntity<List<UserProfileDto>>  getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> create( @Valid @RequestBody CreateUserProfileDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> update(@Valid @RequestBody CreateUserProfileDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AvatarDto> uploadAvatar(@PathVariable Long id, @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(201).body(avatarMapper.toDto(avatarService.uploadAvatar(id, file)));
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<AvatarDto> getAvatar(@PathVariable Long id) {
        return ResponseEntity.ok(avatarMapper.toDto(avatarService.getByUserProfileId(id)));
    }

    @DeleteMapping("/{id}/avatar")
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        try {
            avatarService.deleteAvatar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}