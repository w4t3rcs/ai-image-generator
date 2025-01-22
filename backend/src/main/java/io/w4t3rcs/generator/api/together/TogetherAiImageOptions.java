package io.w4t3rcs.generator.api.together;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.ai.image.ImageOptions;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TogetherAiImageOptions implements ImageOptions {
    private int n = 1;
    private String model = "black-forest-labs/FLUX.1-schnell-Free";
    private int width = 1024;
    private int height = 1024;
    @JsonProperty("response_format")
    private String responseFormat = "base64";
    private long seed;
    private int steps;

    @Override
    public String getStyle() {
        return null;
    }
}
