package com.example.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.video.model.LikeDislike;
import com.example.video.model.Video;

@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Long> {

	  LikeDislike findOneById(Long id);
}
