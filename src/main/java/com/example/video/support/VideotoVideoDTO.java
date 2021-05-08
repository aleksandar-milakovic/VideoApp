package com.example.video.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.video.model.Korisnik;
import com.example.video.model.LikeDislike;
import com.example.video.model.Video;

import com.example.video.web.dto.VideoDTO;
@Component
public class VideotoVideoDTO implements Converter<Video, VideoDTO> {

		@Autowired 
		private KomentarToKomentarDto toKomDto;
		@Override
	    public VideoDTO convert(Video video) {
	        VideoDTO videoDTO = new VideoDTO();

	        videoDTO.setId(video.getId());
	        videoDTO.setBrojPregleda(video.getBrojPregleda());
	        videoDTO.setDatumKreiranja(video.getDatumKreiranja().toString());
	        videoDTO.setDozvoljeniKomentari(video.getDozvoljeniKomentari());
	        videoDTO.setKorisnikId(video.getVlasnik().getId());
	        videoDTO.setOpis(video.getOpis());
	        videoDTO.setSlicica(video.getSlicica());
	        videoDTO.setVideo(video.getVideo());
	        videoDTO.setVidljivost(video.getVidljivost());
	         videoDTO.setVidljivostRejtinga(video.getVidljivostRejtinga());
	        videoDTO.setImeVlasnika(video.getVlasnik().getIme());
	        videoDTO.setPrezimeVlasnika(video.getVlasnik().getPrezime());
	        videoDTO.setBlokiran(video.getBlokiran());
	        videoDTO.setKorisnikBlokiran(video.getVlasnik().getBlokiran() );
	        int like= 0;
	        int dislike=0;
//	        for (LikeDislike ld : video.getLajkovi()) {
//			if(ld.getIsitLike()==true) {
//				like++;
//			}
//		} 
//	        for (LikeDislike ld : video.getLajkovi()) {
//				if(ld.getIsitLike()==false) {
//					dislike++;
//				}
//			}
	        List<Long> lajkovi = new ArrayList<>();
	        List<Long> dislajkovi = new ArrayList<>();
	        List<Long> likedislajkovi = new ArrayList<>();
	        for(LikeDislike id : video.getLajkovi()) {
	        	if(id.getIsitLike()==true) {
	        		lajkovi.add(id.getId());
	        		videoDTO.setIdLajkova(lajkovi);
	        	}else {
	        		dislajkovi.add(id.getId());
	        		videoDTO.setIdDislajkova(dislajkovi);
	        	}
	        }
	        for(LikeDislike id : video.getLajkovi()) {
	        	likedislajkovi.add(id.getId());
	        	videoDTO.setIdLikeDislike(likedislajkovi);
	        }
	       
	        videoDTO.setKomentari(toKomDto.convert(video.getKomentari()));
	       
	        List<Long> idijevi = new ArrayList<>();
	        for (Korisnik korisnik : video.getVlasnik().getPratioci()) {
	        	idijevi.add(korisnik.getId());
				
			}
	        List<Long> listaId = new ArrayList<>();
	        List<String> likesId = new ArrayList<>(video.getMapaLajkova().values());
	        for (String string : likesId) {
				if (string.equalsIgnoreCase("like")) {
					like++;
				}else if (string.equalsIgnoreCase("dislike")){
					dislike++;
				}
			}
	        
	        Map<Long, String> mapaLajkova = (Map<Long, String>) new HashMap();
	        for (Korisnik k : video.getMapaLajkova().keySet()) {
				
	        	mapaLajkova.put(k.getId(), video.getMapaLajkova().get(k));
			}
	        videoDTO.setMapaLajkova(mapaLajkova);
	        videoDTO.setBrojLajkova(like);
	        videoDTO.setBrojDislajkova(dislike);

	               for (Korisnik k : video.getMapaLajkova().keySet()) {
	            	   		listaId.add(k.getId());
	            	    
	               }
	            

	        return videoDTO;
	  
		}

	    public List<VideoDTO> convert(List<Video> videos){
	        List<VideoDTO> videoDTOS = new ArrayList<>();

	        for(Video v : videos) {
	            VideoDTO dto = convert(v);
	            videoDTOS.add(dto);
	        }

	        return videoDTOS;
	    }

		
	
}
