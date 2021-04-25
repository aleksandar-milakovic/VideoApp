package com.example.video.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.video.model.Video;
import com.example.video.service.KorisnikService;
import com.example.video.service.VideoService;
import com.example.video.web.dto.VideoDTO;
import com.fasterxml.jackson.databind.util.Converter;

@Component
public class VideoDTOtoVideo implements org.springframework.core.convert.converter.Converter<VideoDTO, Video>{
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	@Override
	public Video convert(VideoDTO source) {
		Video video = null;
		
		if(source.getId()==null) {
			video = new Video();
		}else {
			video = videoService.findOneId(source.getId());
		}
		if(video !=null) {
			video.setBrojPregleda(source.getBrojPregleda());
			video.setDatumKreiranja(getLocalDate(source.getDatumKreiranja()));
			video.setDozvoljeniKomentari(source.getDozvoljeniKomentari());
			video.setOpis(source.getOpis());
			video.setSlicica(source.getSlicica());
			video.setVideo(source.getVideo());
			video.setVidljivost(source.getVidljivost());
			video.setVidljivostRejtinga(source.getVidljivostRejtinga());
			video.setVlasnik(korisnikService.findOneId(source.getKorisnikId()));
			
		}
		return video;
	}
	
	


private LocalDate getLocalDate(String dateTime) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateTime, formatter);
}}