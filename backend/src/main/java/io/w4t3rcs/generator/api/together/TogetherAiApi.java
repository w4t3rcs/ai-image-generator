package io.w4t3rcs.generator.api.together;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TogetherAiApi {
    private final RestClient restClient;
    private final String apiKey;

    @Autowired
    public TogetherAiApi(TogetherAiApiConfig config, RestClient.Builder builder) {
        TogetherAiApiConfig.Image imageConfig = config.getImage();
        String baseUrl = imageConfig.getBaseUrl();
        String baseUrl1 = baseUrl != null ? baseUrl : config.getBaseUrl();
        String apiKey = imageConfig.getApiKey();
        this.apiKey = apiKey != null ? apiKey : config.getApiKey();
        this.restClient = builder.baseUrl(baseUrl1)
                .defaultHeaders((headers) -> {
                    headers.setBearerAuth(this.apiKey);
                    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .defaultStatusHandler(RetryUtils.DEFAULT_RESPONSE_ERROR_HANDLER)
                .build();
    }

    public GenerateImageResponse generateImage(GenerateImageRequest request) {
        Assert.notNull(request, "Request must not be null");
        Assert.notNull(request.prompt(), "Prompt must not be null");
        return restClient.post()
                .body(request)
                .retrieve()
                .toEntity(GenerateImageResponse.class)
                .getBody();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record GenerateImageRequest(String prompt, Integer n, String model, Integer width,  Integer height, @JsonProperty("response_format") String responseFormat, Long seed, Integer steps) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record GenerateImageResponse(List<Data> data) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Data(String url, @JsonProperty("b64_json") String b64Json) {
    }
}
