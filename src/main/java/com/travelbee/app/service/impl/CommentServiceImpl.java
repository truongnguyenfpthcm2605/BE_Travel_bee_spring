package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Comment;
import com.travelbee.app.repository.CommentRepository;
import com.travelbee.app.service.CommentService;
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
@CacheConfig(cacheNames = "comments")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Comment save(Comment Comment) {
        return commentRepository.save(Comment);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Comment update(Comment Comment) {
        return commentRepository.save(Comment);
    }


    @Override
    @Cacheable(key ="#id",unless="#result == null")
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
