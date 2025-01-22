package io.w4t3rcs.generator.controller;

import io.w4t3rcs.generator.chain.ImageGenerationChain;
import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1.0/images")
@RequiredArgsConstructor
public class ImageGenerationController {
    private final ImageGenerationChain imageGenerationChain;

    @PostMapping
    public ResponseEntity<ImageResponse> generateImage(@RequestBody ImageRequest imageRequest) {
        return ResponseEntity.ok(imageGenerationChain.generate(imageRequest));
    }
}
