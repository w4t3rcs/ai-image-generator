package io.w4t3rcs.generator.service;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;

public interface ImageCompoundService {
    default ImageResponse createImage(ImageRequest request) {
        return createImage(request, 0);
    }

    ImageResponse createImage(ImageRequest request, int elementIndex);

    ImageResponse getImage(Long id);

    Long deleteImage(Long id);
}
