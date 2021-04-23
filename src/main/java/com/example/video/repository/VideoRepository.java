package com.example.video.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.video.model.Korisnik;
import com.example.video.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	  Video findOneById(Long id);
	  
	  
	  @Query("SELECT v FROM Video v WHERE" +
	            "(v.datumKreiranja BETWEEN :datumIVremeOd AND :datumIVremeDo) AND " +
	            "(v.brojPregleda BETWEEN :brojPregledaOd AND :brojPregledaDo) AND "+
	            "( v.opis LIKE %:opis%) AND " +
	            "(:vlasnikId = NULL OR v.vlasnik.id = :vlasnikId) " 
	            )
	    Page<Video> search(@Param("datumIVremeOd") LocalDate datumIVremeOd, @Param("datumIVremeDo") LocalDate datumIVremeDo,
	                            @Param("brojPregledaOd") Integer cenaKarteOd, @Param("brojPregledaDo") Integer cenaKarteDo,
	                            @Param("opis") String opis, @Param("vlasnikId") Long vlasnikId, Pageable pageable);

	
	
}
