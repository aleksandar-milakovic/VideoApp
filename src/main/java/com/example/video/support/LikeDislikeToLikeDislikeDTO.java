package com.example.video.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.example.video.model.LikeDislike;

import com.example.video.web.dto.LikeDislikeDTO;

@Component
public class LikeDislikeToLikeDislikeDTO implements Converter<LikeDislike, LikeDislikeDTO> {

	@Override
	public LikeDislikeDTO convert(LikeDislike source) {
		LikeDislikeDTO likeDto = new LikeDislikeDTO();
		
		likeDto.setDatumKreiranja(source.getDatumKreiranja().toString());
		likeDto.setId(source.getId());
		likeDto.setIsitLike(source.getIsitLike());
		if(source.getKomentar()==null) {
			likeDto.setKomentarId(null);
		}
		else {
		likeDto.setKomentarId(source.getKomentar().getId());
		}
		if(source.getVideo()==null) {
			likeDto.setVideoId(null);
		}else {
		likeDto.setVideoId(source.getVideo().getId());
		}
		return likeDto;
	}
	
	 public List<LikeDislikeDTO> convert(List<LikeDislike> likes){
	        List<LikeDislikeDTO> likesDTOS = new ArrayList<>();

	        for(LikeDislike like : likes) {
	            LikeDislikeDTO dto = convert(like);
	            likesDTOS.add(dto);
	        }

	        return likesDTOS;
	    }
	
}
