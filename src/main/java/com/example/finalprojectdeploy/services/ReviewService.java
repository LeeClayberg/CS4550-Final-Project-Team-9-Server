package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.Review;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ReviewService {
  List<Review> reviews = new ArrayList<Review>();

  //Basic Operations

  @GetMapping("/api/reviews")
  public List<Review> findAllUsers() {
    return reviews;
  }

  @GetMapping("/api/reviews/{reviewId}")
  public Review findReviewById(@PathVariable("reviewId") Integer id) {
    for(Review review: reviews) {
      if(review.getId() == id) {
        return review;
      }
    }
    return null;
  }

  @GetMapping("/api/user/{userId}/reviews")
  public List<Review> findReviewsForUser(@PathVariable("userId") Integer id) {
    List<Review> results = new ArrayList<>(reviews);
    results.removeIf(Review -> Review.getUserId() != id);
    return results;
  }

  @GetMapping("/api/issue/{issueId}/reviews")
  public List<Review> findReviewsForIssue(@PathVariable("issueId") Integer id) {
    List<Review> results = new ArrayList<>(reviews);
    results.removeIf(Review -> Review.getIssueId() != id);
    return results;
  }

  @GetMapping("/api/reviews/recent")
  public List<Review> findRecentReviews() {
    Collections.sort(reviews, new Review.DateAddedComparator());
    return reviews.subList(0, 4);
  }

  @PostMapping("/api/reviews")
  public Review createReview(@RequestBody Review review) {
    reviews.add(review);
    return review;
  }

  @DeleteMapping("/api/reviews/{reviewId}")
  public void deleteReview(@PathVariable("reviewId") Integer id) {
    for(Review review: reviews) {
      if(review.getId() == id) {
        reviews.remove(review);
        return;
      }
    }
  }
}
