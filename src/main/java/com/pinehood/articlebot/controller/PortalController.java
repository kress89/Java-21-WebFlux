package com.pinehood.articlebot.controller;

import com.pinehood.articlebot.model.Portal;
import com.pinehood.articlebot.model.PortalPageContent;
import com.pinehood.articlebot.service.PortalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public record PortalController(PortalService portalService) {
  @GetMapping("/portals")
  public Flux<Portal> getPortalDetails() {
    return portalService.fetchAllPortals();
  }
  @GetMapping("/portals/{portal}")
  public Mono<PortalPageContent> getPortalPageContent(@PathVariable String portal) {
    return portalService.fetchPortalPageContent(portal);
  }

}
