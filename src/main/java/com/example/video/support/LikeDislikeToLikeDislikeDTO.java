package com.example.video.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import com.example.video.model.Komentar;
import com.example.video.model.LikeDislike;
import com.example.video.web.dto.KomentarDTO;
import com.example.video.web.dto.LikeDislikeDTO;

public class LikeDislikeToLikeDislikeDTO implements Converter<LikeDislike, LikeDislikeDTO> {

	@Override
	public LikeDislikeDTO convert(LikeDislike source) {
		LikeDislikeDTO likeDto = new LikeDislikeDTO();
		
		likeDto.setDatumKreiranja(source.getDatumKreiranja().toString());
		likeDto.setId(source.getId());
		likeDto.setIsitLike(source.getIsitLike());
		likeDto.setKomentarId(source.getKomentar().getId());
		likeDto.setVideoId(source.getVideo().getId());
		
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
