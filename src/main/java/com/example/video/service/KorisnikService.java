package com.example.video.service;




import org.springframework.data.domain.Page;

import com.example.video.model.Korisnik;
import com.example.video.web.dto.KorisnikPromenaLozinkeDto;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {

    Optional<Korisnik> findOne(Long id);

    List<Korisnik> findAll();

    Page<Korisnik> findAll(int brojStranice);

    Korisnik save(Korisnik korisnik);

    void delete(Long id);

    Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme);

    boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto);
    
    Korisnik findOneId(Long id);

    Page<Korisnik> find(String eMail,String ime,String korIme,String prezime,String uloga,int pageNo );
    
}
