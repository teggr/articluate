package com.teggr.articluate.controller;

import com.teggr.articluate.model.ArticleRequest;
import com.teggr.articluate.model.ArticleResponse;
import com.teggr.articluate.service.ArticleService;
import com.teggr.articluate.ui.UiRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UiController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("youtubeUrl")) {
            model.addAttribute("youtubeUrl", "");
        }
        return "homeView";
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String generate(@RequestParam(name = "youtubeUrl", required = false) String youtubeUrl, Model model) {
        String normalizedUrl = normalize(youtubeUrl);
        model.addAttribute("youtubeUrl", normalizedUrl);
        applyGeneration(model, normalizedUrl);
        return "homeView";
    }

    @PostMapping(value = "/ui/articles", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String generatePartial(@RequestParam(name = "youtubeUrl", required = false) String youtubeUrl) {
        String normalizedUrl = normalize(youtubeUrl);
        if (normalizedUrl.isBlank()) {
            return UiRenderer.renderResultContent(null, "Please provide a YouTube URL.");
        }

        try {
            ArticleResponse article = articleService.generate(new ArticleRequest(normalizedUrl));
            return UiRenderer.renderResultContent(article, null);
        } catch (RuntimeException ex) {
            return UiRenderer.renderResultContent(null, ex.getMessage());
        }
    }

    private void applyGeneration(Model model, String normalizedUrl) {
        model.addAttribute("article", null);
        model.addAttribute("error", null);

        if (normalizedUrl.isBlank()) {
            model.addAttribute("error", "Please provide a YouTube URL.");
            return;
        }

        try {
            model.addAttribute("article", articleService.generate(new ArticleRequest(normalizedUrl)));
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
        }
    }

    private String normalize(String youtubeUrl) {
        return youtubeUrl == null ? "" : youtubeUrl.trim();
    }
}
