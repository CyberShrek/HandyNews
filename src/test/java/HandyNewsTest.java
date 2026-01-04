import com.cybershrek.simplenews.HandyNews;

import java.io.IOException;
import java.util.Set;

public class HandyNewsTest {
    public static void main() throws IOException {
        HandyNews news = new HandyNews(Set.of("cnn", "bbc-news", "google-news-ru"));
        news.getArticles().forEach(System.out::println);
    }
}
