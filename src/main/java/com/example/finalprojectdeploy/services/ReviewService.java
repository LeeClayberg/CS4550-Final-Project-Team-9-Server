package com.example.finalprojectdeploy.services;

import com.example.finalprojectdeploy.model.HistoryAction;
import com.example.finalprojectdeploy.model.Review;
import com.example.finalprojectdeploy.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

  @Autowired
  ReviewRepository reviewRepository;
  @Autowired
  HistoryService historyService = new HistoryService();

  //Basic Operations

  public List<Review> findAllReviews() {
    return reviewRepository.findAllReviews();
  }

  public Review findReviewById(Integer id) {
    return reviewRepository.findReviewById(id);
  }

  public List<Review> findReviewsForUser(Integer id) {
    return reviewRepository.findReviewsForUser(id);
  }

  public List<Review> findReviewsForIssue(Integer id) {
    return reviewRepository.findReviewsForIssue(id);
  }

  public List<Review> findRecentReviews() {
    List<Review> reviews = reviewRepository.findRecentReviews();
    return reviews.size() > 4 ? reviews.subList(0, 4) : reviews;
  }

  public Review createReview(Review review) {
    historyService.createHistoryAction(new HistoryAction(review.getUserId(), "reviewed", review.getIssueId()));
    return reviewRepository.save(review);
  }

  public void deleteReview(Integer id) {
    Review beingDeleted = reviewRepository.findReviewById(id);
    historyService.createHistoryAction(new HistoryAction("review deleted", beingDeleted.getIssueId()));
    reviewRepository.deleteById(id);
  }
}
