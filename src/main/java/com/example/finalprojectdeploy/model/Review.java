package com.example.finalprojectdeploy.model;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="reviews")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int issueId;
  private int userId;
  private String username;
  private int stars;
  private String text;
  private String coverImageURL;
  private String timestamp;

  public Review(int issueId, int userId, String username, int stars, String text, String coverImageURL, String timestamp) {
    this.issueId = issueId;
    this.userId = userId;
    this.username = username;
    this.stars = stars;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
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
      return b.timestamp.compareTo(a.timestamp);
    }
  }
}
