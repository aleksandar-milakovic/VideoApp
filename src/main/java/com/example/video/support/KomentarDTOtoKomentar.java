package com.example.video.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.video.model.Komentar;
import com.example.video.model.Video;
import com.example.video.service.KomentarService;
import com.example.video.service.KorisnikService;
import com.example.video.service.VideoService;
import com.example.video.web.dto.KomentarDTO;
@Component
public class KomentarDTOtoKomentar implements Converter<KomentarDTO, Komentar> {

	@Autowired
	private KomentarService komentarService;
	
	@Autowired
	private VideoService 	videoService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	
	
	@Override
	public Komentar convert(KomentarDTO source) {
		Komentar komentar = null;
		
		if(source.getId()==null) {
			komentar = new Komentar();
		}else {
			komentar = komentarService.findOneId(source.getId());
		}
		if(komentar !=null) {
			komentar.setDatumKreiranja(getLocalDate(source.getDatumKreiranja()));
			komentar.setSadrzaj(source.getSadrzaj());
			komentar.setVideo(videoService.findOneId(source.getVideoId()));
			komentar.setVlasnik(korisnikService.findOneId(source.getVlasnikId()));
			
			
		}
		return komentar;
		}
		
		private LocalDate getLocalDate(String dateTime) throws DateTimeParseException {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    return LocalDate.parse(dateTime, formatter);
		}

}
