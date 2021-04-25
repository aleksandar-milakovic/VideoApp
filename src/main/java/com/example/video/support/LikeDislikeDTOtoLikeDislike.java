package com.example.video.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.example.video.model.LikeDislike;
import com.example.video.model.Video;
import com.example.video.service.KomentarService;
import com.example.video.service.LikeDislikeService;
import com.example.video.service.VideoService;
import com.example.video.web.dto.LikeDislikeDTO;

public class LikeDislikeDTOtoLikeDislike implements Converter<LikeDislikeDTO, LikeDislike> {

	@Autowired
	private LikeDislikeService likeService;
	
	@Autowired
	private KomentarService 	komService;
	
	@Autowired
	private VideoService 		videoService;
	
	
	@Override
	public LikeDislike convert(LikeDislikeDTO source) {
		LikeDislike like = null;
		
		if(source.getId()==null) {
			like = new LikeDislike();
		}else {
			like = likeService.findOneId(source.getId());
		}
		if(like !=null) {
			
			like.setDatumKreiranja(getLocalDate(source.getDatumKreiranja()));
			like.setIsitLike(source.getIsitLike());
			like.setKomentar(komService.findOneId(source.getKomentarId()));
			like.setVideo(videoService.findOneId(source.getVideoId()));
	
	}
		return like;
		
	}

	private LocalDate getLocalDate(String dateTime) throws DateTimeParseException {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return LocalDate.parse(dateTime, formatter);
	}
}
