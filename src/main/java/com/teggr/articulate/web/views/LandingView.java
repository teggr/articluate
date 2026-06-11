package com.teggr.articulate.web.views;

import dev.rebelcraft.j2html.spring.webmvc.J2HtmlView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import j2html.tags.DomContent;
import org.springframework.stereotype.Component;

import java.util.Map;

import static j2html.TagCreator.a;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.main;
import static j2html.TagCreator.p;

@Component("landingView")
public class LandingView extends J2HtmlView {

    @Override
    protected DomContent renderMergedOutputModelDomContent(Map<String, Object> model,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        String loginUrl = (String) model.getOrDefault("loginUrl", "/login");

        return Page.render(
                "Articulate",
                "landing-page",
                main().withClass("page").with(
                        div().withClass("jumbotron").with(
                                h1("Articulate turns YouTube videos into polished articles."),
                                p("Paste a video URL and Articulate fetches transcripts, cleans noisy speech patterns, and generates structured long-form writing with readable HTML and Markdown output."),
                                p("Use it to quickly repurpose talks, tutorials, and interviews into blog-ready content without writing from scratch.")
                        ),
                        div().withClass("panel").with(
                                p("Ready to generate your first article? Sign in to open the generator workspace."),
                                a("Log in to continue").withClass("login-cta").withHref(loginUrl)
                        )
                )
        );
    }
}
