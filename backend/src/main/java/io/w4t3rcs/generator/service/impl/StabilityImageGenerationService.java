package io.w4t3rcs.generator.service.impl;

import io.w4t3rcs.generator.service.AbstractImageGenerationService;
import org.springframework.ai.image.ImageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StabilityImageGenerationService extends AbstractImageGenerationService {
    public StabilityImageGenerationService(@Qualifier("stabilityAiImageModel") ImageModel imageModel) {
        super(imageModel);
    }
}
