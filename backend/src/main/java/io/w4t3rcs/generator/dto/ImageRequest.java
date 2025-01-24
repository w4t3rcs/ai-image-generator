package io.w4t3rcs.generator.dto;

import java.io.Serial;
import java.io.Serializable;

public record ImageRequest(String prompt) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
