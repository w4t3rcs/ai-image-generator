package io.w4t3rcs.generator.service;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;

public interface ImageGenerationService {
    ImageResponse createImage(ImageRequest imageRequest);
}
