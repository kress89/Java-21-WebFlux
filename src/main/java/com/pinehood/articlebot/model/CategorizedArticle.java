package com.pinehood.articlebot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategorizedArticle extends Article {
  private String category;

  public CategorizedArticle(Article article, String category) {
    super(article.getArticleId(), article.getArticleLink(), article.getAuthor(), article.getContent(),
    article.getLead(), article.getPortalLink(), article.getPortalName(), article.getTitle(), article.getTime());
    this.category = category;
  }
}
