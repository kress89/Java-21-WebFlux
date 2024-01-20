package com.pinehood.articlebot.model;

import lombok.Data;

@Data
public class ScrapingStatistics {
  private int articles;
  private long duration;
  private long date;
}
