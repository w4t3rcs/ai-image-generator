package io.w4t3rcs.generator.service.impl;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;
import io.w4t3rcs.generator.exception.ChainIndexOutOfBoundsException;
import io.w4t3rcs.generator.repository.ImageRepository;
import io.w4t3rcs.generator.service.ImageGenerationCompoundService;
import io.w4t3rcs.generator.service.ImageGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AiImageGenerationCompoundService implements ImageGenerationCompoundService {
    private final List<ImageGenerationService> generationServices;
    private final ImageRepository imageRepository;

    @Autowired
    public AiImageGenerationCompoundService(TogetherImageGenerationService togetherImageGenerationService,
                                            StabilityImageGenerationService stabilityImageGenerationService,
                                            ZhiPuImageGenerationService zhiPuImageGenerationService,
                                            ImageRepository imageRepository) {
        this.generationServices = List.of(togetherImageGenerationService, stabilityImageGenerationService, zhiPuImageGenerationService);
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageResponse createImage(ImageRequest request, int elementIndex) {
        try {
            if (elementIndex >= generationServices.size()) throw new ChainIndexOutOfBoundsException();
            ImageResponse response = generationServices.get(elementIndex).createImage(request);
            log.info("Generated an AI image (response with id : {}, request : {})", response.getId(), request);
            return response;
        } catch (ChainIndexOutOfBoundsException e) {
            throw new ChainIndexOutOfBoundsException();
        } catch (Exception e) {
            log.warn("Something went wrong with spring ai service: {}", e.getMessage());
            return createImage(request, elementIndex + 1);
        }
    }

    @Override
    public ImageResponse getImage(Long id) {
        Image image = imageRepository.findById(id);
        return ImageResponse.of(id, image);
    }

    @Override
    public Long deleteImage(Long id) {
        imageRepository.deleteById(id);
        return id;
    }
}
