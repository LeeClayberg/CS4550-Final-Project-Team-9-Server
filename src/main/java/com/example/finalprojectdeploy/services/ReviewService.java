package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.model.Review;
import com.example.finalprojectdeploy.repositories.ReviewRepository;

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
public class ReviewService {

  @Autowired
  ReviewRepository reviewRepository;

  HistoryService historyService = new HistoryService();

  //Basic Operations

  @GetMapping("/api/reviews")
  public List<Review> findAllReviews() {
    return reviewRepository.findAllReviews();
  }

  @GetMapping("/api/reviews/{reviewId}")
  public Review findReviewById(@PathVariable("reviewId") Integer id) {
    return reviewRepository.findReviewById(id);
  }

  @GetMapping("/api/users/{userId}/reviews")
  public List<Review> findReviewsForUser(@PathVariable("userId") Integer id) {
    return reviewRepository.findReviewsForUser(id);
  }

  @GetMapping("/api/issues/{issueId}/reviews")
  public List<Review> findReviewsForIssue(@PathVariable("issueId") Integer id) {
    return reviewRepository.findReviewsForIssue(id);
  }

  @GetMapping("/api/reviews/recent")
  public List<Review> findRecentReviews() {
    List<Review> reviews = reviewRepository.findRecentReviews();
    return reviews.size() > 4 ? reviews.subList(0, 4) : reviews;
  }

  @PostMapping("/api/reviews")
  public Review createReview(@RequestBody Review review) {
    historyService.createHistoryAction(new HistoryAction(review.getUserId(), "reviewed", review.getIssueId()));
    return reviewRepository.save(review);
  }

  @DeleteMapping("/api/reviews/{reviewId}")
  public void deleteReview(@PathVariable("reviewId") Integer id) {
    Review beingDeleted = reviewRepository.findReviewById(id);
    historyService.createHistoryAction(new HistoryAction(beingDeleted.getIssueId(), "review deleted by"));
    reviewRepository.deleteById(id);
  }
}
