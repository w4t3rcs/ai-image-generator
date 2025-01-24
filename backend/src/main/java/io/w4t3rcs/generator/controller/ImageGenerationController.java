package io.w4t3rcs.generator.controller;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;
import io.w4t3rcs.generator.service.ImageGenerationCompoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/images")
@RequiredArgsConstructor
public class ImageGenerationController {
    private final ImageGenerationCompoundService imageGenerationService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageGenerationService.getImage(id));
    }

    @PostMapping
    public ResponseEntity<ImageResponse> generateImage(@RequestBody ImageRequest imageRequest) {
        return ResponseEntity.ok(imageGenerationService.createImage(imageRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageGenerationService.deleteImage(id));
    }
}
