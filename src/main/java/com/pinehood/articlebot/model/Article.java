package com.pinehood.articlebot.model;

import lombok.Data;

@Data
public class Article {
  private String articleId;
  private String articleLink;
  private String author;
  private String content;
  private String lead;
  private String portalLink;
  private String portalName;
  private String title;
  private String time;

  public Article() {
  }

  public Article(String articleId, String articleLink, String author, String content, String lead, String portalLink, String portalName, String title, String time) {
    this.articleId = articleId;
    this.articleLink = articleLink;
    this.author = author;
    this.content = content;
    this.lead = lead;
    this.portalLink = portalLink;
    this.portalName = portalName;
    this.title = title;
    this.time = time;
  }

  @Override
  public String toString() {
    return "Article{" +
    "articleId='" + articleId + '\'' +
    ", articleLink='" + articleLink + '\'' +
    ", author='" + author + '\'' +
    ", content='" + content + '\'' +
    ", lead='" + lead + '\'' +
    ", portalLink='" + portalLink + '\'' +
    ", portalName='" + portalName + '\'' +
    ", title='" + title + '\'' +
    ", time=" + time +
    '}';
  }

}
