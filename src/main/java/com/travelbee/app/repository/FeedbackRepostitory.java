package com.travelbee.app.repository;

import com.travelbee.app.enities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepostitory extends JpaRepository<Feedback,Long> {
}
