package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Feedback;
import com.travelbee.app.repository.FeedbackRepostitory;
import com.travelbee.app.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "feedbacks")
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepostitory feedbackRepostitory;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Feedback save(Feedback Feedback) {
        return feedbackRepostitory.save(Feedback);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Feedback update(Feedback Feedback) {
        return feedbackRepostitory.save(Feedback);
    }

    @Override
    @Cacheable(key = "#id",unless="#result == null")
    public Optional<Feedback> findById(Long id) {
        return feedbackRepostitory.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        feedbackRepostitory.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Feedback> findAll() {
        return feedbackRepostitory.findAll();
    }
}
