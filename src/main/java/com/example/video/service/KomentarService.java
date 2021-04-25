package com.example.video.service;

import org.springframework.data.domain.Page;

import com.example.video.model.Komentar;
import com.example.video.model.Video;

public interface KomentarService {
	
	 Page<Komentar> findAll(int pageNo);

	 Komentar save(Komentar komentar);

	 Komentar delete(Long id);
	 
	 Komentar findOneId(Long id);

}
