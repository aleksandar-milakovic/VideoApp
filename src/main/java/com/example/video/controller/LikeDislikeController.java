package com.example.video.controller;

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


import com.example.video.model.LikeDislike;
import com.example.video.service.LikeDislikeService;
import com.example.video.support.LikeDislikeDTOtoLikeDislike;
import com.example.video.support.LikeDislikeToLikeDislikeDTO;
import com.example.video.web.dto.LikeDislikeDTO;

@RestController
@RequestMapping(value = "/api/lajkovi", produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeDislikeController {

	@Autowired
    private LikeDislikeService	likeService;

    @Autowired
    private LikeDislikeDTOtoLikeDislike toLike;

    @Autowired
    private LikeDislikeToLikeDislikeDTO toLikeDto;

 

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<LikeDislikeDTO> create(@RequestBody @Validated LikeDislikeDTO dto){

        if(dto.getId() != null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       
        LikeDislike like = toLike.convert(dto);

        return new ResponseEntity<>(toLikeDto.convert(likeService.save(like)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeDislikeDTO> update(@PathVariable Long id, @Valid @RequestBody LikeDislikeDTO dto){

        if(!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

      LikeDislike like = toLike.convert(dto);


        return new ResponseEntity<>(toLikeDto.convert(likeService.save(like)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LikeDislikeDTO> get(@PathVariable Long id){
        LikeDislike like = likeService.findOneId(id);

        if(like !=null) {
            return new ResponseEntity<>(toLikeDto.convert(like), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")

    @GetMapping
    public ResponseEntity<List<LikeDislikeDTO>> get(
    		

    		
    		@RequestParam(defaultValue="0") int page) {
    		

		Page<LikeDislike> like = likeService.findAll(page);
    		
    		
    	
    		
    	
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(like.getTotalPages()));

        
        return new ResponseEntity<>(toLikeDto.convert(like.getContent()),headers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
	  
		
	     
    	LikeDislike like  = likeService.findOneId(id);
	  

		
		
		
	  LikeDislike likeDis = likeService.delete(id);
	 
	    if(likeDis != null) { 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
