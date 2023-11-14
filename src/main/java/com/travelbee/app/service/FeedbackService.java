package com.travelbee.app.service;

import com.travelbee.app.enities.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    Feedback save(Feedback Feedback);
    Feedback update(Feedback Feedback);
    Optional<Feedback> findById(Long id);
    void deleteById(Long id);
    List<Feedback> findAll();
}
