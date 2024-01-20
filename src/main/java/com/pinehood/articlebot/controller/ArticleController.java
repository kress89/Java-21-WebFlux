package com.pinehood.articlebot.controller;

import com.pinehood.articlebot.model.Article;
import com.pinehood.articlebot.model.CategorizedArticle;
import com.pinehood.articlebot.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public record ArticleController (ArticleService articleService) {

  @GetMapping("/articles/{portal}")
  public Flux<Article> getArticles(
  @PathVariable String portal,
  @RequestParam(defaultValue = "false") boolean withContent) {
    return articleService.fetchArticles(portal, withContent);
  }

  @GetMapping("/portals/article/{portal}/{articleId}")
  public Mono<Article> getArticleContent(@PathVariable String portal, @PathVariable String articleId) {
    return articleService.fetchArticleContent(portal, articleId);

  }

  @GetMapping("/articles/category/{portal}")
  public Flux<CategorizedArticle> getCategorizedArticles(@PathVariable String portal,
                                                         @RequestParam(defaultValue = "false") boolean withContent) {
    return articleService.fetchAndCategorizeArticles(portal, withContent);
  }

  @GetMapping("/fetch/cache")
  public String fetchAndCacheArticles() {
    articleService.fetchAndCacheArticles();
    return "Fetching and caching articles started.";
  }

  @GetMapping("/articles/classify")
  public List<CategorizedArticle> classifyAndFetchArticles() {
    return articleService.classifyArticles();
  }

}
