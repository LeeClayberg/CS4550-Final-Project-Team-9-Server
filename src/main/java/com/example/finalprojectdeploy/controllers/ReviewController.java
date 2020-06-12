package com.example.finalprojectdeploy.controllers;

import com.example.finalprojectdeploy.model.Review;
import com.example.finalprojectdeploy.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReviewController {

  @Autowired
  ReviewService reviewService;

  @GetMapping("/api/reviews")
  public List<Review> findAllReviews() {
    return reviewService.findAllReviews();
  }

  @GetMapping("/api/reviews/{reviewId}")
  public Review findReviewById(@PathVariable("reviewId") Integer id) {
    return reviewService.findReviewById(id);
  }

  @GetMapping("/api/users/{userId}/reviews")
  public List<Review> findReviewsForUser(@PathVariable("userId") Integer id) {
    return reviewService.findReviewsForUser(id);
  }

  @GetMapping("/api/issues/{issueId}/reviews")
  public List<Review> findReviewsForIssue(@PathVariable("issueId") Integer id) {
    return reviewService.findReviewsForIssue(id);
  }

  @GetMapping("/api/reviews/recent")
  public List<Review> findRecentReviews() {
    return reviewService.findRecentReviews();
  }

  @PostMapping("/api/reviews")
  public Review createReview(@RequestBody Review review) {
    return reviewService.createReview(review);
  }

  @DeleteMapping("/api/reviews/{reviewId}")
  public void deleteReview(@PathVariable("reviewId") Integer id) {
    reviewService.deleteReview(id);
  }
}
