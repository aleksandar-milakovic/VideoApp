package com.example.video.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.video.model.Video;
import com.example.video.repository.VideoRepository;
import com.example.video.service.VideoService;
import com.sun.el.stream.Optional;

@Service
public class JpaVideoService implements VideoService {
	
	@Autowired
	private VideoRepository VideoRepository;
	
	@Override
	public Page<Video> findAll(int pageNo) {
		
		return VideoRepository.findAll(PageRequest.of(pageNo, 4));
	}

	@Override
	public Video save(Video video) {
		return VideoRepository.save(video);
	}

	@Override
	public Video delete(Long id) {
java.util.Optional<Video> por=  VideoRepository.findById(id);
		
		if (por.isPresent()){
		VideoRepository.deleteById(id);
		//porRep.delete(por.get());
			
			
			return por.get();
		}
		return null;
	}

	@Override
	public Video findOneId(Long id) {
		return VideoRepository.findOneById(id);
	}
	@Override
	public Page<Video> find(LocalDate datumIVremeOd, LocalDate datumIVremeDo, Integer brojPregledaOd,
			Integer brojPregledaDo, String opis, Long vlasnikId, int pageNo) {
		if(datumIVremeOd==null) {
			datumIVremeOd=LocalDate.MIN;}
		if(datumIVremeDo==null) {
			datumIVremeDo=LocalDate.MAX;}
		if(brojPregledaOd==null) {
			brojPregledaOd=Integer.MIN_VALUE;
		}
		if(brojPregledaDo==null) {
			brojPregledaDo=Integer.MAX_VALUE;
		}
		return VideoRepository.search(datumIVremeOd, datumIVremeDo, brojPregledaOd, brojPregledaDo, opis, vlasnikId, PageRequest.of(pageNo, 4));
		}
		
		}

	


