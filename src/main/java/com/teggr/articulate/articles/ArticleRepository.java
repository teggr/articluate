package com.teggr.articulate.articles;

import java.util.Optional;
import java.util.List;

public interface ArticleRepository {

    Optional<ArticleResult> findById(String id);

    Optional<ArticleResult> findByTranscriptId(String transcriptId);

    List<ArticleResult> findAll();

    void save(ArticleResult article);
}
