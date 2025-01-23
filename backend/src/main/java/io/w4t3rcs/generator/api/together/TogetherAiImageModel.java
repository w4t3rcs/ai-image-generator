package io.w4t3rcs.generator.api.together;

import org.springframework.ai.image.*;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TogetherAiImageModel implements ImageModel {
    private final TogetherAiApi togetherAiApi;
    private final TogetherAiImageOptions defaultOptions;

    @Autowired
    public TogetherAiImageModel(TogetherAiApi togetherAiApi, TogetherAiApiConfig config) {
        this.togetherAiApi = togetherAiApi;
        this.defaultOptions = config.getImage().getOption();
    }

    @Override
    public ImageResponse call(ImagePrompt request) {
        TogetherAiImageOptions imageOptions = this.mergeOptions(request.getOptions());
        TogetherAiApi.GenerateImageRequest generateImageRequest = this.createRequest(request, imageOptions);
        TogetherAiApi.GenerateImageResponse generateImageResponse = this.togetherAiApi.generateImage(generateImageRequest);
        return convertResponse(generateImageResponse);
    }

    private TogetherAiImageOptions mergeOptions(ImageOptions otherOptions) {
        if (otherOptions == null) return defaultOptions;
        TogetherAiImageOptions.TogetherAiImageOptionsBuilder builder = TogetherAiImageOptions.builder()
                .n(ModelOptionsUtils.mergeOption(defaultOptions.getN(), otherOptions.getN()))
                .model(ModelOptionsUtils.mergeOption(defaultOptions.getModel(), otherOptions.getModel()))
                .width(ModelOptionsUtils.mergeOption(defaultOptions.getWidth(), otherOptions.getWidth()))
                .height(ModelOptionsUtils.mergeOption(defaultOptions.getHeight(), otherOptions.getHeight()))
                .responseFormat(ModelOptionsUtils.mergeOption(defaultOptions.getResponseFormat(), otherOptions.getResponseFormat()));
        if (otherOptions instanceof TogetherAiImageOptions togetherAiImageOptions) {
            builder.seed(togetherAiImageOptions.getSeed())
                    .steps(togetherAiImageOptions.getSteps());
        }

        return builder.build();
    }

    private TogetherAiApi.GenerateImageRequest createRequest(ImagePrompt imagePrompt, TogetherAiImageOptions imageOptions) {
        return new TogetherAiApi.GenerateImageRequest(imagePrompt.getInstructions()
                .stream()
                .map(ImageMessage::getText)
                .collect(Collectors.joining("\n")),
                imageOptions.getN(), imageOptions.getModel(), imageOptions.getWidth(), imageOptions.getHeight(), imageOptions.getResponseFormat(), imageOptions.getSeed(), imageOptions.getSteps());
    }

    private ImageResponse convertResponse(TogetherAiApi.GenerateImageResponse generateImageResponse) {
        List<ImageGeneration> imageGenerations = generateImageResponse.data()
                .stream()
                .map(data -> new Image(data.url(), data.b64Json()))
                .map(ImageGeneration::new)
                .toList();
        return new ImageResponse(imageGenerations);
    }
}
