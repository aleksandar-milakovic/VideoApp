package com.example.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.video.model.Komentar;
import com.example.video.model.Video;

public interface KomentarRepository extends JpaRepository<Komentar, Long>{

	 Komentar findOneById(Long id);
}
