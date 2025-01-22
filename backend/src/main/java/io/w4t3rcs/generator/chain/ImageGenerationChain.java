package io.w4t3rcs.generator.chain;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;

public interface ImageGenerationChain {
    default ImageResponse generate(ImageRequest request) {
        return generate(request, 0);
    }

    ImageResponse generate(ImageRequest request, int elementIndex);
}
