package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.Review;

import org.springframework.web.bind.annotation.CrossOrigin;
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

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/reviews")
  public List<Review> findAllUsers() {
    return reviews;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/reviews/{reviewId}")
  public Review findReviewById(@PathVariable("reviewId") Integer id) {
    for(Review review: reviews) {
      if(review.getId() == id) {
        return review;
      }
    }
    return null;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/user/{userId}/reviews")
  public List<Review> findReviewsForUser(@PathVariable("userId") Integer id) {
    List<Review> results = new ArrayList<>(reviews);
    results.removeIf(Review -> Review.getUserId() != id);
    return results;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/issue/{issueId}/reviews")
  public List<Review> findReviewsForIssue(@PathVariable("issueId") Integer id) {
    List<Review> results = new ArrayList<>(reviews);
    results.removeIf(Review -> Review.getIssueId() != id);
    return results;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/api/reviews/recent")
  public List<Review> findRecentReviews() {
    Collections.sort(reviews, new Review.DateAddedComparator());
    return reviews.subList(0, 4);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/reviews")
  public Review createReview(@RequestBody Review review) {
    reviews.add(review);
    return review;
  }

  @CrossOrigin(origins = "http://localhost:3000")
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
