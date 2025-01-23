package io.w4t3rcs.generator.api.together;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.ai.togetherai")
public class TogetherAiApiConfig {
    private String apiKey;
    private String baseUrl = "https://api.together.xyz/v1";
    private Image image = new Image();

    @Data
    public static class Image {
        private boolean enabled;
        private String baseUrl = "https://api.together.xyz/v1/images/generations";
        private String apiKey;
        private TogetherAiImageOptions option = TogetherAiImageOptions.builder()
                .n(1)
                .model("black-forest-labs/FLUX.1-schnell-Free")
                .width(1024)
                .height(1024)
                .responseFormat("base64")
                .build();
    }
}
