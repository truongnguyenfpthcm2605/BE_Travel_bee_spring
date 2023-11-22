package com.travelbee.app.service;

import com.travelbee.app.enities.Likes;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Likes save(Likes Likes);
    Likes update(Likes Likes);
    Optional<Likes> findById(Long id);
    void deleteById(Long id);
    List<Likes> findAll();

    Long findAllLike(Long id);
    Likes findByAccount(@Param("id")Long accountId);

}
