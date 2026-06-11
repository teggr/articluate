package com.teggr.articulate.articles.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.teggr.articulate.articles.ArticleResult;
import com.teggr.articulate.articles.ArticleResponse;
import com.teggr.articulate.articles.ArticleService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ArticlesController.class)
class ArticlesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArticleService articleService;

    @Test
    void getArticlesReturnsArticlesView() throws Exception {
        List<ArticleResult> articles = List.of(
                new ArticleResult("a2", "2026-06-11T10:00:00Z", "t2", "Newest", "# newest"),
                new ArticleResult("a1", "2026-06-10T10:00:00Z", "t1", "Older", "# older")
        );
        when(articleService.findAll()).thenReturn(articles);

        mockMvc.perform(get("/articles").with(user("test-user")))
                .andExpect(status().isOk())
                .andExpect(view().name("articlesView"))
                .andExpect(model().attribute("articles", articles));
    }

    @Test
    void getArticleDetailReturnsDetailView() throws Exception {
        ArticleResult stored = new ArticleResult("a2", "2026-06-11T10:00:00Z", "t2", "Newest", "# newest");
        ArticleResponse response = new ArticleResponse("Newest", "# newest", "<h1>newest</h1>");
        when(articleService.findById("a2")).thenReturn(Optional.of(stored));
        when(articleService.findResponseById("a2")).thenReturn(Optional.of(response));

        mockMvc.perform(get("/articles/a2").with(user("test-user")))
                .andExpect(status().isOk())
                .andExpect(view().name("articleDetailView"))
                .andExpect(model().attribute("article", response))
                .andExpect(model().attribute("articleId", "a2"))
                .andExpect(model().attribute("createdAt", "2026-06-11T10:00:00Z"));
    }

    @Test
    void getArticleDetailReturns404WhenMissing() throws Exception {
        when(articleService.findById("missing")).thenReturn(Optional.empty());

        mockMvc.perform(get("/articles/missing").with(user("test-user")))
                .andExpect(status().isNotFound());
    }
}
