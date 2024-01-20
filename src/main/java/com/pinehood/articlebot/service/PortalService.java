package com.pinehood.articlebot.service;

import com.pinehood.articlebot.model.PortalPageContent;
import com.pinehood.articlebot.model.Portal;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public record PortalService(WebClient webClient) {

  public Flux<Portal> fetchAllPortals() {
    return webClient.get()
    .uri("/api/portals")
    .retrieve()
    .bodyToFlux(Portal.class);
  }

  public Mono<PortalPageContent> fetchPortalPageContent(String portal) {
    return webClient.get()
    .uri("/portals/{portal}", portal)
    .retrieve()
    .bodyToMono(PortalPageContent.class);
  }
}
