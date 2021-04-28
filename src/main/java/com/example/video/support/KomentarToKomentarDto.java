package com.example.video.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.video.model.Komentar;
import com.example.video.model.LikeDislike;
import com.example.video.model.Video;
import com.example.video.web.dto.KomentarDTO;
import com.example.video.web.dto.VideoDTO;
@Component
public class KomentarToKomentarDto implements Converter<Komentar, KomentarDTO> {

	@Override
	public KomentarDTO convert(Komentar source) {
		KomentarDTO komDto = new KomentarDTO();
		
		komDto.setDatumKreiranja(source.getDatumKreiranja().toString());
		komDto.setSadrzaj(source.getSadrzaj());
		komDto.setVideoId(source.getVideo().getId());
		komDto.setVlasnikId(source.getVlasnik().getId());
		komDto.setId(source.getId());
		  int like= 0;
	        int dislike=0;
	        for (LikeDislike ld : source.getLajkovi()) {
			if(ld.getIsitLike()==true) {
				like++;
			}
		}
	        for (LikeDislike ld : source.getLajkovi()) {
				if(ld.getIsitLike()==false) {
					dislike++;
				}
			}
	        
	        komDto.setBrojLajkova(like);
	        komDto.setBrojDislajkova(dislike);
	        komDto.setImeVlasnika(source.getVlasnik().getIme());
	     
	        
		return komDto;
	}
	
	  public List<KomentarDTO> convert(List<Komentar> komentari){
	        List<KomentarDTO> komentarDTOS = new ArrayList<>();

	        for(Komentar k : komentari) {
	            KomentarDTO dto = convert(k);
	            komentarDTOS.add(dto);
	        }

	        return komentarDTOS;
	    }

}
