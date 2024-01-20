package com.pinehood.articlebot.service;

import com.pinehood.articlebot.model.Article;
import com.pinehood.articlebot.model.CategorizedArticle;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public record ArticleService(WebClient webClient , ApplicationContext applicationContext,
                             RedisTemplate<String, Object> redisTemplate, ArticleClassificationService articleClassificationService) {

  public Flux<Article> fetchArticles(String portal, boolean withContent) {
    return webClient.get()
    .uri(uriBuilder -> uriBuilder
    .path("/api/articles/{portal}")
    .queryParam("withContent", withContent)
    .build(portal))
    .retrieve()
    .bodyToFlux(Article.class);

  }

  public void fetchAndCacheArticles() {
    String[] portals = {
    "24sata", "index", "vecernji", "jutarnji", "net", "dnevnik",
    "dnevno", "tportal", "sd", "sn", "direktno", "poslovni",
    "danas", "zagreb", "telegram", "novilist", "n1info",
    "nacional", "nacionalno", "otvoreno"
    };

    ValueOperations<String, Object> ops = redisTemplate.opsForValue();

    Flux.fromArray(portals)
    .flatMap(portal -> fetchArticles(portal, true)
    .doOnNext(article -> ops.set(buildRedisKey(portal, article.getArticleId()), article)))
    .subscribe();
  }

  private String buildRedisKey(String portal, String articleId) {
    return "article:" + portal + ":" + articleId;
  }

  public Flux<CategorizedArticle> fetchAndCategorizeArticles(String portal, boolean withContent) {
    ArticleClassificationService classificationService = applicationContext.getBean(ArticleClassificationService.class);

    return fetchArticles(portal, withContent)
    .flatMap(article -> Mono.just(classificationService.classifyArticle(article)))
    .cast(CategorizedArticle.class);
  }

  public Mono<Article> fetchArticleContent(String portal, String articleId) {
    return webClient.get()
    .uri("/portals/article/{portal}/{articleId}", portal, articleId)
    .retrieve()
    .bodyToMono(Article.class);

  }

  public List<Article> getAllArticlesFromRedis() {
    ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
    Set<String> keys = redisTemplate.keys("article:*");
    List<Article> articles = new ArrayList<>();

    if (keys != null) {
      for (String key : keys) {
        Article article = (Article) valueOps.get(key);
        if (article != null) {
          articles.add(article);
        }
      }
    }

    return articles;
  }

  public List<CategorizedArticle> classifyArticles() {
    List<Article> articles = getAllArticlesFromRedis();
    return articles.stream()
    .map(articleClassificationService::classifyArticle)
    .collect(Collectors.toList());


  }
}
