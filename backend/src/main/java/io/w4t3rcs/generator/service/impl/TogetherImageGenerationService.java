package io.w4t3rcs.generator.service.impl;

import io.w4t3rcs.generator.repository.ImageRepository;
import io.w4t3rcs.generator.service.AbstractImageGenerationService;
import org.springframework.ai.image.ImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TogetherImageGenerationService extends AbstractImageGenerationService {
    @Autowired
    public TogetherImageGenerationService(@Qualifier("togetherAiImageModel") ImageModel imageModel, ImageRepository imageRepository) {
        super(imageModel, imageRepository);
    }
}
