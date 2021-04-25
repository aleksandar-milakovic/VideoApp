package com.example.video.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.video.model.Komentar;
import com.example.video.model.Video;
import com.example.video.service.KomentarService;
import com.example.video.service.VideoService;
import com.example.video.support.KomentarDTOtoKomentar;
import com.example.video.support.KomentarToKomentarDto;
import com.example.video.support.VideoDTOtoVideo;
import com.example.video.support.VideotoVideoDTO;
import com.example.video.web.dto.KomentarDTO;
import com.example.video.web.dto.VideoDTO;

@RestController
@RequestMapping(value = "/api/komentari", produces = MediaType.APPLICATION_JSON_VALUE)
public class KomentarController {
	
	@Autowired
    private KomentarService	komService;

    @Autowired
    private KomentarDTOtoKomentar toKomentar;

    @Autowired
    private KomentarToKomentarDto toKomentarDto;

 

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<KomentarDTO> create(@RequestBody @Validated KomentarDTO dto){

        if(dto.getId() != null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       
        Komentar komentar = toKomentar.convert(dto);

        return new ResponseEntity<>(toKomentarDto.convert(komService.save(komentar)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KomentarDTO> update(@PathVariable Long id, @Valid @RequestBody KomentarDTO dto){

        if(!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

      Komentar komentar = toKomentar.convert(dto);


        return new ResponseEntity<>(toKomentarDto.convert(komService.save(komentar)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<KomentarDTO> get(@PathVariable Long id){
        Komentar komentar = komService.findOneId(id);

        if(komentar !=null) {
            return new ResponseEntity<>(toKomentarDto.convert(komentar), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")

    @GetMapping
    public ResponseEntity<List<KomentarDTO>> get(
    		

    		
    		@RequestParam(defaultValue="0") int page) {
    		

		Page<Komentar> komentari = komService.findAll(page);
    		
    		
    	
    		
    	
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(komentari.getTotalPages()));

        
        return new ResponseEntity<>(toKomentarDto.convert(komentari.getContent()),headers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
	  
		
	     
    	Komentar komentar= komService.findOneId(id);
	  

		
		
		
	  Komentar obrisanKomentar = komService.delete(id);
	 // Festival sacuvaniZadatak = porService.save(obrisanZadatak);
	    if(obrisanKomentar != null) { 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}
