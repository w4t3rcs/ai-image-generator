package io.w4t3rcs.generator.service;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;
import io.w4t3rcs.generator.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;

@RequiredArgsConstructor
public abstract class AbstractImageGenerationService implements ImageGenerationService {
    private final ImageModel imageModel;
    private final ImageRepository imageRepository;

    @Override
    public ImageResponse createImage(ImageRequest imageRequest) {
        Image output = imageModel.call(new ImagePrompt(imageRequest.prompt()))
                .getResult()
                .getOutput();
        Long id = imageRepository.save(output);
        return ImageResponse.of(id, output);
    }
}
