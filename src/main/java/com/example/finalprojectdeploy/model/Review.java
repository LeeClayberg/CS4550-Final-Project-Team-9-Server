package com.example.finalprojectdeploy.model;

import java.util.Comparator;

public class Review {
  private int id;
  private int issueId;
  private int userId;
  private String text;
  private String coverImageURL;
  private String timestamp;

  public Review(int id, int issueId, int userId, String text, String coverImageURL, String timestamp) {
    this.id = id;
    this.issueId = issueId;
    this.userId = userId;
    this.text = text;
    this.coverImageURL = coverImageURL;
    this.timestamp = timestamp;
  }

  public Review() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIssueId() {
    return issueId;
  }

  public void setIssueId(int issueId) {
    this.issueId = issueId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getCoverImageURL() {
    return coverImageURL;
  }

  public void setCoverImageURL(String coverImageURL) {
    this.coverImageURL = coverImageURL;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public static class DateAddedComparator implements Comparator<Review>
  {
    public int compare(Review a, Review b)
    {
      return a.timestamp.compareTo(b.timestamp);
    }
  }
}
