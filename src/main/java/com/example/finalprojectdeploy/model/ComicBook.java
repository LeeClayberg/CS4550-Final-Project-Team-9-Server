package com.example.finalprojectdeploy.model;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comic_books")
public class ComicBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int issueId;
  private int userId;
  private double grade;
  private String signatures;
  private String coverImageURL;
  private String coverDate;
  private String timestamp;
  private String title;
  private String volume;
  private String characters;

  public ComicBook(int issueId, int userId, double grade, String signatures, String coverImageURL, String coverDate, String timestamp, String title, String volume, String characters) {
    this.issueId = issueId;
    this.userId = userId;
    this.grade = grade;
    this.signatures = signatures;
    this.coverImageURL = coverImageURL;
    this.coverDate = coverDate;
    this.timestamp = timestamp;
    this.title = title;
    this.volume = volume;
    this.characters = characters;
  }

  public ComicBook() {
  }

  public void updateComicBook(ComicBook comicBook) {
    this.grade = comicBook.grade;
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

  public double getGrade() {
    return grade;
  }

  public void setGrade(double grade) {
    this.grade = grade;
  }

  public String getSignatures() {
    return signatures;
  }

  public void setSignatures(String signatures) {
    this.signatures = signatures;
  }

  public String getCoverImageURL() {
    return coverImageURL;
  }

  public void setCoverImageURL(String coverImageURL) {
    this.coverImageURL = coverImageURL;
  }

  public String getCoverDate() {
    return coverDate;
  }

  public void setCoverDate(String coverDate) {
    this.coverDate = coverDate;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }

  public String getCharacters() {
    return characters;
  }

  public void setCharacters(String characters) {
    this.characters = characters;
  }

  //Comparator

  static class GradeComparator implements Comparator<ComicBook>
  {
    public int compare(ComicBook a, ComicBook b)
    {
      return (int)((b.grade - a.grade) * 10);
    }
  }

  public static class CoverDateComparator implements Comparator<ComicBook>
  {
    public int compare(ComicBook a, ComicBook b)
    {
      return a.coverDate.compareTo(b.coverDate);
    }
  }

  public static class DateAddedComparator implements Comparator<ComicBook>
  {
    public int compare(ComicBook a, ComicBook b)
    {
      return a.timestamp.compareTo(b.timestamp);
    }
  }

  public static class TitleComparator implements Comparator<ComicBook>
  {
    public int compare(ComicBook a, ComicBook b)
    {
      return a.title.compareTo(b.title);
    }
  }

  public static class VolumeComparator implements Comparator<ComicBook>
  {
    public int compare(ComicBook a, ComicBook b)
    {
      return a.volume.compareTo(b.volume);
    }
  }
}
