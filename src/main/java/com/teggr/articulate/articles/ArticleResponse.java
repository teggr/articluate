package com.teggr.articulate.articles;

public record ArticleResponse(
        String title,
        String markdown,
        String html
) {
}
