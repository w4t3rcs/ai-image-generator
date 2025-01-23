package io.w4t3rcs.generator.chain;

import io.w4t3rcs.generator.dto.ImageRequest;
import io.w4t3rcs.generator.dto.ImageResponse;
import io.w4t3rcs.generator.exception.ChainIndexOutOfBoundsException;
import io.w4t3rcs.generator.service.ImageGenerationService;
import io.w4t3rcs.generator.service.impl.StabilityImageGenerationService;
import io.w4t3rcs.generator.service.impl.TogetherImageGenerationService;
import io.w4t3rcs.generator.service.impl.ZhiPuImageGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AiImageGenerationChain implements ImageGenerationChain {
    private final List<ImageGenerationService> generationServices;

    @Autowired
    public AiImageGenerationChain(TogetherImageGenerationService togetherImageGenerationService,
                                  StabilityImageGenerationService stabilityImageGenerationService,
                                  ZhiPuImageGenerationService zhiPuImageGenerationService) {
        this.generationServices = List.of(togetherImageGenerationService, stabilityImageGenerationService, zhiPuImageGenerationService);
    }

    @Override
    public ImageResponse generate(ImageRequest request, int elementIndex) {
        try {
            if (elementIndex >= generationServices.size()) throw new ChainIndexOutOfBoundsException();
            ImageResponse response = generationServices.get(elementIndex).createImage(request);
            log.info("Generated an AI image (response with id : {}, request : {})", response.getId(), request);
            return response;
        } catch (ChainIndexOutOfBoundsException e) {
            throw new ChainIndexOutOfBoundsException();
        } catch (Exception e) {
            log.warn("Something went wrong with spring ai service: {}", e.getMessage());
            return generate(request, elementIndex + 1);
        }
    }
}
