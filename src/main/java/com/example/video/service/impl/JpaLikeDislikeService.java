package com.example.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.video.model.Komentar;
import com.example.video.model.LikeDislike;
import com.example.video.repository.LikeDislikeRepository;
import com.example.video.service.LikeDislikeService;

@Service
public class JpaLikeDislikeService implements LikeDislikeService{
	
	@Autowired
	private LikeDislikeRepository likeDislikeRep;
	
	
	@Override
	public Page<LikeDislike> findAll(int pageNo) {
		
		return likeDislikeRep.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public LikeDislike save(LikeDislike likeDislike) {
	
		return likeDislikeRep.save(likeDislike);
	}

	@Override
	public LikeDislike delete(Long id) {
java.util.Optional<LikeDislike> like  =  likeDislikeRep.findById(id);
		
		if (like.isPresent()){
			likeDislikeRep.deleteById(id);
		
			
			
			return like.get();
		}
		return null;
	}

	@Override
	public LikeDislike findOneId(Long id) {
		
		return likeDislikeRep.findOneById(id);
	}

}
