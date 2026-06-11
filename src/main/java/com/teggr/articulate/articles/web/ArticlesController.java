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

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping
    public String index(Model model) {
        var articles = articleService.findAll();
        Map<String, String> sourceUrls = new LinkedHashMap<>();
        for (var article : articles) {
            articleService.findSourceUrlByTranscriptId(article.transcriptId())
                    .ifPresent(url -> sourceUrls.put(article.id(), url));
        }

        model.addAttribute("articles", articles);
        model.addAttribute("sourceUrls", sourceUrls);
        return "articlesView";
    }

    @GetMapping("/{articleId}")
    public String show(@PathVariable String articleId, Model model) {
        var article = articleService.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
        model.addAttribute("article", articleService.findResponseById(articleId).orElseThrow());
        model.addAttribute("articleId", article.id());
        model.addAttribute("createdAt", article.createdAt());
        model.addAttribute("sourceUrl", articleService.findSourceUrlByTranscriptId(article.transcriptId()).orElse(""));
        model.addAttribute("youtubeEmbedUrl", articleService.findYoutubeEmbedUrlByTranscriptId(article.transcriptId()).orElse(""));
        return "articleDetailView";
    }
}
