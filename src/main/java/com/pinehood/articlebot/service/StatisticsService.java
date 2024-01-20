package com.pinehood.articlebot.service;

import com.pinehood.articlebot.model.PortalStatistics;
import com.pinehood.articlebot.model.ScrapingStatistics;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public record StatisticsService(WebClient webClient) {

  public Mono<ScrapingStatistics> fetchStatistics() {
    return webClient.get()
    .uri("/api/stats")
    .retrieve()
    .bodyToMono(ScrapingStatistics.class);
  }

  public Mono<PortalStatistics> fetchPortalStatistics(String portal) {
    return webClient.get()
    .uri("/api/stats/{portal}", portal)
    .retrieve()
    .bodyToMono(PortalStatistics.class);
  }
}
