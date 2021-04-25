package com.example.video.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;


import com.example.video.model.Video;

import com.example.video.web.dto.VideoDTO;

public class VideotoVideoDTO implements Converter<Video, VideoDTO> {

		@Override
	    public VideoDTO convert(Video video) {
	        VideoDTO videoDTO = new VideoDTO();

	        videoDTO.setId(video.getId());
	        videoDTO.setBrojPregleda(video.getBrojPregleda());
	        videoDTO.setDatumKreiranja(video.getDatumKreiranja().toString());
	        videoDTO.setDozvoljeniKomentari(video.getDozvoljeniKomentari());
	        videoDTO.setKorisnikId(video.getVlasnik().getId());
	        videoDTO.setOpis(video.getOpis());
	        videoDTO.setSlicica(video.getSlicica());
	        videoDTO.setVideo(video.getVideo());
	        videoDTO.setVidljivost(video.getVidljivost());
	        videoDTO.setVidljivostRejtinga(video.getVidljivostRejtinga());
	        

	        return videoDTO;
	    }

	    public List<VideoDTO> convert(List<Video> videos){
	        List<VideoDTO> videoDTOS = new ArrayList<>();

	        for(Video v : videos) {
	            VideoDTO dto = convert(v);
	            videoDTOS.add(dto);
	        }

	        return videoDTOS;
	    }

		
	
}
