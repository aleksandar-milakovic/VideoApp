package com.example.video.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.video.enumeration.KorisnickaUloga;
import com.example.video.model.Korisnik;


import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findFirstByKorisnickoIme(String korisnickoIme);

    Optional<Korisnik> findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);

    @Query("SELECT k FROM Korisnik k WHERE" +
            "(k.eMail LIKE %:eMail%) AND " +
            "(k.ime LIKE %:ime%) AND "+
            "( k.korisnickoIme LIKE %:korisnickoIme%) AND " +
            "(k.prezime LIKE %:prezime%) AND " +
            "(:uloga = NULL OR k.uloga LIKE :uloga)")
    Page<Korisnik> search(@Param("eMail")String eMail, @Param("ime") String ime,
                            @Param("korisnickoIme") String korisnickoIme, @Param("prezime") String prezime,
                            @Param("uloga") KorisnickaUloga uloga,Pageable pageable);

    Korisnik findOneById(Long id);

}
