package com.pinehood.articlebot.model;

import lombok.Data;

import java.util.List;

@Data
public class PortalPageContent {
  private List<Article> articles;
}
