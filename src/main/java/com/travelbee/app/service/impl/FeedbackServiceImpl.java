package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Feedback;
import com.travelbee.app.repository.FeedbackRepostitory;
import com.travelbee.app.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepostitory feedbackRepostitory;

    @Override
    public Feedback save(Feedback Feedback) {
        return feedbackRepostitory.save(Feedback);
    }

    @Override
    public Feedback update(Feedback Feedback) {
        return feedbackRepostitory.save(Feedback);
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return feedbackRepostitory.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        feedbackRepostitory.deleteById(id);
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepostitory.findAll();
    }
}
