package com.teggr.articulate.web.views;

import jakarta.servlet.http.HttpServletRequest;
import j2html.tags.DomContent;
import j2html.tags.specialized.HeadTag;
import j2html.tags.specialized.BodyTag;
import org.springframework.security.web.csrf.CsrfToken;

import static j2html.TagCreator.a;
import static j2html.TagCreator.body;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.form;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.input;
import static j2html.TagCreator.link;
import static j2html.TagCreator.meta;
import static j2html.TagCreator.title;

public final class Page {

    private Page() {
    }

    public static DomContent render(String pageTitle, DomContent bodyContent, DomContent... headExtras) {
      return render(pageTitle, null, bodyContent, headExtras);
        }

    public static DomContent primaryNav(String activePath, HttpServletRequest request) {
      return div().withClass("primary-nav").with(
          navLink("Generate", "/generate", "/generate".equals(activePath)),
          navLink("Articles", "/articles", "/articles".equals(activePath)),
          logoutForm(request)
      );
    }

    private static DomContent navLink(String text, String href, boolean active) {
      if (active) {
        return a(text).withHref(href).withClass("is-active");
      }
      return a(text).withHref(href);
    }

    private static DomContent logoutForm(HttpServletRequest request) {
      CsrfToken csrfToken = request == null
          ? null
          : (CsrfToken) request.getAttribute(CsrfToken.class.getName());

      if (csrfToken == null) {
        return form().withMethod("post").withAction("/logout")
            .with(button("Logout").withType("submit").withClass("logout-button"));
      }

      return form().withMethod("post").withAction("/logout")
          .with(
              input().withType("hidden")
                  .withName(csrfToken.getParameterName())
                  .withValue(csrfToken.getToken()),
              button("Logout").withType("submit").withClass("logout-button")
          );
    }

        public static DomContent render(String pageTitle,
                String bodyClass,
                DomContent bodyContent,
                DomContent... headExtras) {
        HeadTag headTag = head(
                meta().withCharset("UTF-8"),
                meta().attr("name", "viewport").attr("content", "width=device-width, initial-scale=1"),
                title(pageTitle),
        link().withRel("stylesheet").withHref("/app.css")
        );

        if (headExtras != null && headExtras.length > 0) {
            headTag.with(headExtras);
        }

      BodyTag bodyTag = body(bodyContent);
      if (bodyClass != null && !bodyClass.isBlank()) {
          bodyTag.withClass(bodyClass);
      }

        return html(
                headTag,
        bodyTag
        );
    }
}
