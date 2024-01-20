package com.pinehood.articlebot.service;

import org.springframework.data.redis.core.RedisTemplate;

public record RedisService(
RedisTemplate<String, Object> redisTemplate) {

  public void saveArticle(String key, Object article) {
    redisTemplate.opsForValue().set(key, article);
  }

  public Object getArticle(String key) {
    return redisTemplate.opsForValue().get(key);
  }
}
