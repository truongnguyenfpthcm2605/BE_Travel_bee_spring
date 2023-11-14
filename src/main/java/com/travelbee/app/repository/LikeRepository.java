package com.travelbee.app.repository;

import com.travelbee.app.enities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
}
