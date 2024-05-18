package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Comment;
import com.travelbee.app.repository.CommentRepository;
import com.travelbee.app.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    public Comment save(Comment Comment) {
        return commentRepository.save(Comment);
    }

    @Override
    public Comment update(Comment Comment) {
        return commentRepository.save(Comment);
    }


    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByTourId(Long tourId) {
        return commentRepository.findByTourId(tourId);
    }


}
