package com.teggr.articulate.articles.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.teggr.articulate.articles.ArticleService;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "articlesView";
    }

    @GetMapping("/{articleId}")
    public String show(@PathVariable String articleId, Model model) {
        var article = articleService.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
        model.addAttribute("article", articleService.findResponseById(articleId).orElseThrow());
        model.addAttribute("articleId", article.id());
        model.addAttribute("createdAt", article.createdAt());
        return "articleDetailView";
    }
}
