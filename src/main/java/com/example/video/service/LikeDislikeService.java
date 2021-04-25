package com.example.video.service;

import org.springframework.data.domain.Page;

import com.example.video.model.LikeDislike;



public interface LikeDislikeService {
	
	 Page<LikeDislike> findAll(int pageNo);

	 LikeDislike save(LikeDislike likeDislike);

	 LikeDislike delete(Long id);
	 
	 LikeDislike findOneId(Long id);


}
