package io.w4t3rcs.generator.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.image.Image;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String imageBase64;

    public static ImageResponse of(Long id, Image image) {
        return new ImageResponse(id, image.getB64Json());
    }
}
