package com.teggr.articulate.transcripts;

import java.util.Optional;

public interface TranscriptRepository {

    Optional<TranscriptResult> findById(String id);

    void save(TranscriptResult transcript);
}
