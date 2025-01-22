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
    private Image image;

    @Data
    public static class Image {
        private boolean enabled;
        private String baseUrl = "https://api.together.xyz/v1/images/generations";
        private String apiKey;
        private TogetherAiImageOptions option;
    }
}
