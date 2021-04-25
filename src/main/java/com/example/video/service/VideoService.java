package com.example.video.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;


import com.example.video.model.Video;

public interface VideoService {
	
	 Page<Video> findAll(int pageNo);

	 Video save(Video video);

	 Video delete(Long id);
	 
	 Video findOneId(Long id);

	 Page<Video> find(LocalDate datumIVremeOd,LocalDate datumIVremeDo,Integer brojPregledaOd,Integer brojPregledaDo,String opis,Long vlasnikId,int pageNo );
}
