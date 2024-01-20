package com.pinehood.articlebot.controller;

import com.pinehood.articlebot.model.PortalStatistics;
import com.pinehood.articlebot.model.ScrapingStatistics;
import com.pinehood.articlebot.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public record StatisticsController(StatisticsService statisticsService) {
  @GetMapping("/stats")
  public Mono<ScrapingStatistics> getStatistics() {
    return statisticsService.fetchStatistics();
  }

  @GetMapping("/stats/{portal}")
  public Mono<PortalStatistics> getPortalStatistics(@PathVariable String portal) {
    return statisticsService.fetchPortalStatistics(portal);

  }
}
