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
    private Integer n;
    private String model;
    private Integer width;
    private Integer height;
    @JsonProperty("response_format")
    private String responseFormat;
    private Long seed;
    private Integer steps;

    @Override
    public String getStyle() {
        return null;
    }
}
