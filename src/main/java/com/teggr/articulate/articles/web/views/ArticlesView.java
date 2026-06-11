package com.teggr.articulate.articles.web.views;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import j2html.tags.DomContent;
import org.springframework.stereotype.Component;

import com.teggr.articulate.articles.ArticleResult;
import com.teggr.articulate.web.views.Page;

import java.util.List;
import java.util.Map;

import static j2html.TagCreator.a;
import static j2html.TagCreator.article;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.main;
import static j2html.TagCreator.p;
import static j2html.TagCreator.time;
import static j2html.TagCreator.text;

@Component("articlesView")
public class ArticlesView extends J2HtmlView {

    @Override
    @SuppressWarnings("unchecked")
    protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        List<ArticleResult> articles = (List<ArticleResult>) model.getOrDefault("articles", List.of());

        return Page.render(
                "Articles | Articulate",
                "articles-page",
                main().withClass("page").with(
                        div().withClass("topbar").with(
                                h1("Your Articles"),
                                Page.primaryNav("/articles", request)
                        ),
                        p("Newest articles appear first.").withClass("intro"),
                        articles.isEmpty()
                                ? div("No articles have been created yet.").withClass("empty")
                                : div(articles.stream()
                                        .map(this::articleCard)
                                        .toArray(DomContent[]::new)).withClass("list")
                )
        );
    }

    private DomContent articleCard(ArticleResult article) {
        return article().withClass("card").with(
                h2(a(article.title()).withHref("/articles/" + article.id())),
                p().withClass("meta").with(
                        text("Created "),
                        time(article.createdAt()).attr("datetime", article.createdAt())
                )
        );
    }
}
