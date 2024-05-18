package com.travelbee.app.service.impl;

import com.travelbee.app.enities.Likes;
import com.travelbee.app.repository.LikeRepository;
import com.travelbee.app.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Likes save(Likes Likes) {
        return likeRepository.save(Likes);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Likes update(Likes Likes) {
        return likeRepository.save(Likes);
    }

    @Override
    public Optional<Likes> findById(Long id) {
        return likeRepository.findById(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    public List<Likes> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Long findAllLike(Long id) {
        return likeRepository.findAllLike(id);
    }

    @Override
    public Likes findByAccount(Long accountId, Long tourId) {
        return likeRepository.findByAccount(accountId,tourId);
    }

}
