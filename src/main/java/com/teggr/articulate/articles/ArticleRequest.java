package com.teggr.articulate.articles;

import jakarta.validation.constraints.NotBlank;

public record ArticleRequest(
        @NotBlank(message = "youtubeUrl must not be blank")
        String youtubeUrl
) {
}
