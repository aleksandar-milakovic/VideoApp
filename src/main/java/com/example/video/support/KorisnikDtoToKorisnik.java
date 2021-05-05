package com.example.video.support;






import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.video.enumeration.KorisnickaUloga;
import com.example.video.model.Korisnik;
import com.example.video.service.KorisnikService;
import com.example.video.web.dto.KorisnikDTO;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik> {

    @Autowired
    private KorisnikService korisnikService;


    @Override
    public Korisnik convert(KorisnikDTO korisnikDTO) {
        Korisnik entity = null;

        if(korisnikDTO.getId() == null) {
            entity = new Korisnik();
        }else {
            Optional<Korisnik> korisnikOptional = korisnikService.findOne(korisnikDTO.getId());
            if(korisnikOptional.isPresent()){
                entity = korisnikOptional.get();
            }
        }

        if(entity != null) {
            entity.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
           
            entity.seteMail(korisnikDTO.geteMail());
            entity.setIme(korisnikDTO.getIme());
            entity.setPrezime(korisnikDTO.getPrezime());
            entity.setBlokiran(korisnikDTO.getBlokiran());
            if(korisnikDTO.getUloga().equalsIgnoreCase("ADMIN")) {
            	entity.setUloga(KorisnickaUloga.ADMIN);
            	System.out.println("bababababb");
            }else {
            	entity.setUloga(KorisnickaUloga.KORISNIK);
            }
            
            System.out.println(entity.getUloga());  
        }

        return entity;
    }

}
