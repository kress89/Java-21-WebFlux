package com.pinehood.articlebot.service;

import com.pinehood.articlebot.model.Article;
import com.pinehood.articlebot.model.CategorizedArticle;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public record ArticleClassificationService(KieContainer kieContainer) {
  public CategorizedArticle classifyArticle(Article article) {
    KieSession kieSession = kieContainer.newKieSession();
    Map<Article, String> articleCategoryMap = new HashMap<>();
    kieSession.setGlobal("articleCategoryMap", articleCategoryMap);
    try {
      kieSession.insert(article);
      kieSession.fireAllRules();

      String category = articleCategoryMap.get(article);
      return new CategorizedArticle(article, category);
    } finally {
      kieSession.dispose();
    }
  }

}
