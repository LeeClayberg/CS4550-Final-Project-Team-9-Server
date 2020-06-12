package com.example.finalprojectdeploy.repositories;


import com.example.finalprojectdeploy.model.Review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {

  @Query("SELECT review FROM Review review")
  public List<Review> findAllReviews();

  @Query("SELECT review FROM Review review WHERE review.id=:reviewId")
  public Review findReviewById(@Param("reviewId") Integer id);

  @Query("SELECT review FROM Review review WHERE review.userId=:userId")
  public List<Review> findReviewsForUser(@Param("userId") Integer id);

  @Query("SELECT review FROM Review review WHERE review.issueId=:issueId")
  public List<Review> findReviewsForIssue(@Param("issueId") Integer id);

  @Query("SELECT review FROM Review review ORDER BY timestamp DESC")
  public List<Review> findRecentReviews();
}
