package com.travelbee.app.repository;

import com.travelbee.app.enities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {

    @Query("SELECT COUNT(l) FROM Likes l WHERE l.tour.id = :tourId and l.isactive = true")
    Long findAllLike(@Param("tourId") Long tourId);

    @Query("select o from Likes o where o.account.id = :id ")
    Likes findByAccount(@Param("id")Long accountId);

    @Query("select o from Likes o where o.account.id = :id and o.tour.id=:tourId ")
    Likes findByAccount(@Param("id") Long accountId,@Param("tourId") Long tourId);
}
