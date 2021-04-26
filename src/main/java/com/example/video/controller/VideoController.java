package com.example.video.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.video.model.Video;
import com.example.video.service.VideoService;
import com.example.video.support.VideoDTOtoVideo;
import com.example.video.support.VideotoVideoDTO;
import com.example.video.web.dto.AuthKorisnikDto;

import com.example.video.web.dto.VideoDTO;

@RestController
@RequestMapping(value = "/api/videos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VideoController {
	
	@Autowired
    private VideoService	videoService;

    @Autowired
    private VideoDTOtoVideo toVideo;

    @Autowired
    private VideotoVideoDTO toVideoDto;

 

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<VideoDTO> create(@RequestBody @Validated VideoDTO dto){

        if(dto.getId() != null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       
        Video video = toVideo.convert(dto);

        return new ResponseEntity<>(toVideoDto.convert(videoService.save(video)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VideoDTO> update(@PathVariable Long id, @Valid @RequestBody VideoDTO videoDTO){

        if(!id.equals(videoDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

      Video video = toVideo.convert(videoDTO);


        return new ResponseEntity<>(toVideoDto.convert(videoService.save(video)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> get(@PathVariable Long id){
        Video video = videoService.findOneId(id);

        if(video !=null) {
            return new ResponseEntity<>(toVideoDto.convert(video), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")

    @GetMapping
    public ResponseEntity<List<VideoDTO>> get(
    		@RequestParam(required = false ) String datumIVremeOdParametar,
    		@RequestParam(required = false ) String datumIVremeDoParametar,
    		@RequestParam(required = false) Integer brojPregledaOd,
    		@RequestParam(required = false) Integer brojPregledaDo,
    		@RequestParam(required = false, defaultValue = "") String opis,
    		 @RequestParam(value = "totalPages", defaultValue = "0") int totalPages,
    		@RequestParam(required = false ) Long vlasnikId,

    		
    		@RequestParam(value="pageNo",defaultValue="0") int pageNo) {
    		

		Page<Video> videos = null;
    		
    		try {
    		LocalDate datumIVremeOd = getLocalDate(datumIVremeOdParametar);
    
    		
    		LocalDate datumIVremeDo = getLocalDate(datumIVremeDoParametar);
        
    	
    		 videos = videoService.find(datumIVremeOd, datumIVremeDo, brojPregledaOd, brojPregledaDo, opis, vlasnikId, pageNo);}
    		catch (Exception e) {
				videos = videoService.findAll(pageNo);
			}
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(videos.getTotalPages()));

        
        return new ResponseEntity<>(toVideoDto.convert(videos.getContent()),headers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
	  
		
	     
    	Video video= videoService.findOneId(id);
	  

		
		
		
	  Video obrisanVideo = videoService.delete(id);
	 // Festival sacuvaniZadatak = porService.save(obrisanZadatak);
	    if(obrisanVideo != null) { 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    private LocalDate getLocalDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTime, formatter);
    }

   
}


