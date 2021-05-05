package com.example.video.support;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.video.model.Korisnik;
import com.example.video.web.dto.KorisnikDTO;



import java.util.ArrayList;
import java.util.List;

@Component
public class KorisnikToKorisnikDto implements Converter<Korisnik, KorisnikDTO>{
	
	@Autowired
	private VideotoVideoDTO toVideoDto;
	
	
	@Autowired
	private KorisnikToKorisnikDto toKorisnikDto;
	
    @Override
    public KorisnikDTO convert(Korisnik korisnik) {
        KorisnikDTO korisnikDTO = new KorisnikDTO();

        korisnikDTO.setId(korisnik.getId());
        
        korisnikDTO.seteMail(korisnik.geteMail());
        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setKorisnickoIme(korisnik.getKorisnickoIme());
        korisnikDTO.setUloga(korisnik.getUloga().toString());
        korisnikDTO.setOpis(korisnik.getOpisKanala());
        korisnikDTO.setBlokiran(korisnik.getBlokiran());
        korisnikDTO.setBrojPratilaca(korisnik.getPratioci().size());
        korisnikDTO.setVidei(toVideoDto.convert(korisnik.getVidei()));
        korisnikDTO.setDatumKreiranja(korisnik.getDatumReg().toString());
      
        korisnikDTO.setLozinka(korisnik.getLozinka());
        List<Long> idijevi = new ArrayList<>();
        for (Korisnik korisnik1 : korisnik.getPratioci()) {
        	idijevi.add(korisnik1.getId());
			
		}
        korisnikDTO.setIdpratioca(idijevi);
        korisnikDTO.setPratioci(toKorisnikDto.convert(korisnik.getPratioci2()));
        return korisnikDTO;
    }

    public List<KorisnikDTO> convert(List<Korisnik> korisnici){
        List<KorisnikDTO> korisnikDTOS = new ArrayList<>();

        for(Korisnik k : korisnici) {
            KorisnikDTO dto = convert(k);
            korisnikDTOS.add(dto);
        }

        return korisnikDTOS;
    }
}
