package com.cybershrek.simplenews;

import com.cybershrek.tools.HandyClient;
import com.cybershrek.tools.HandyResources;
import com.cybershrek.tools.JSON;
import com.cybershrek.simplenews.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class HandyNews {

    private final HandyClient client = new HandyClient();

    public HandyNews(Set<String> sources) {
        Properties props = HandyResources.loadProperties("newsapi.properties");
        client.url(String.format("%s?apiKey=%s&sources=%s",
                        props.getProperty("newsapi.url"),
                        props.getProperty("newsapi.key"),
                        String.join(",", sources)));
    }

    public List<NewsArticle> getArticles() {
        List<NewsArticle> articles = new ArrayList<>();
        JSON.parse(client.GET().body()).get("articles").iterator().forEachRemaining(node -> {

            String title = node.get("title").asText();
            String source = node.get("source").get("name").asText();
            String author = node.get("author").asText();
            String content = node.get("content").asText();

            if (!author.isEmpty() && !source.equals(author)) {
                source = source + " - " + author;
            }

            articles.add(new NewsArticle(title, source, content));
        });
        return articles;
    }
}
