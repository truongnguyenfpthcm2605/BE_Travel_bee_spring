package com.travelbee.app.service;

import com.travelbee.app.enities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment Comment);
    Comment update(Comment Comment);
    Optional<Comment> findById(Long id);
    void deleteById(Long id);
    List<Comment> findAll();
}
