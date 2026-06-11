package com.teggr.articulate.articles.web.views;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.article;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.iframe;
import static j2html.TagCreator.p;
import static j2html.TagCreator.rawHtml;

import com.teggr.articulate.articles.ArticleResponse;

public final class UiRenderer {

    private UiRenderer() {
    }

    public static DivTag resultContainer(ArticleResponse article, String error, String youtubeEmbedUrl) {
        return div(resultContent(article, error, youtubeEmbedUrl)).withId("article-result");
    }

    public static DomContent resultContent(ArticleResponse article, String error, String youtubeEmbedUrl) {
        if (error != null && !error.isBlank()) {
            return div(
                    h3("Unable to generate article"),
                    p(error)
            );
        }

        if (article == null) {
            return div(p("No article yet."));
        }

        DomContent videoSection = (youtubeEmbedUrl == null || youtubeEmbedUrl.isBlank())
                ? div()
                : div(
                        h3("Video"),
                        div(
                                iframe()
                                        .withClass("video-frame")
                                        .attr("title", "YouTube video player")
                                        .withSrc(youtubeEmbedUrl)
                                        .attr("allow", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share")
                                        .attr("allowfullscreen", "allowfullscreen")
                        ).withClass("video-embed")
                );

        return article().with(
                h2(article.title()),
                videoSection,
                h3("Rendered output"),
                div(rawHtml(article.html()))
        );
    }

    public static String renderResultContent(ArticleResponse article, String error, String youtubeEmbedUrl) {
        return resultContent(article, error, youtubeEmbedUrl).render();
    }
}
