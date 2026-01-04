package com.cybershrek.simplenews.model;

import java.time.LocalDateTime;

public record NewsArticle(
        String title,
        String source,
        String content
) {
}
