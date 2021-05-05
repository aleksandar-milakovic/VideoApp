package com.example.video.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.video.enumeration.KorisnickaUloga;
import com.example.video.model.Korisnik;
import com.example.video.model.Video;
import com.example.video.security.TokenUtils;
import com.example.video.service.KorisnikService;
import com.example.video.support.KorisnikDtoToKorisnik;
import com.example.video.support.KorisnikToKorisnikDto;
import com.example.video.web.dto.AuthKorisnikDto;
import com.example.video.web.dto.KorisnikDTO;
import com.example.video.web.dto.KorisnikPromenaLozinkeDto;
import com.example.video.web.dto.KorisnikRegistracijaDTO;
import com.example.video.web.dto.VideoDTO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikDtoToKorisnik toKorisnik;

    @Autowired
    private KorisnikToKorisnikDto toKorisnikDto;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<KorisnikDTO> create(@RequestBody @Validated KorisnikRegistracijaDTO dto){
    		System.out.println(dto.getLozinka()+"  "+dto.getPonovljenaLozinka());
        if(!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter za njega
        // ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
        Korisnik korisnik = toKorisnik.convert(dto);
//        if(korisnik.getAdresa() == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        // dodatak za zadatak 1
        String encodedPassword = passwordEncoder.encode(dto.getLozinka());
        korisnik.setLozinka(encodedPassword);

        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDTO> update(@PathVariable Long id, @Valid @RequestBody KorisnikDTO korisnikDTO){

        if(!id.equals(korisnikDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = toKorisnik.convert(korisnikDTO);
//
//        if(korisnik.getAdresa()  == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> get(@PathVariable Long id){
        Optional<Korisnik> korisnik = korisnikService.findOne(id);

        if(korisnik.isPresent()) {
            return new ResponseEntity<>(toKorisnikDto.convert(korisnik.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")

    @GetMapping
    public ResponseEntity<List<KorisnikDTO>> get(
    		@RequestParam(required = false ,defaultValue = "") String eMail,
    		@RequestParam(required = false, defaultValue = "") String ime,
    		@RequestParam(required = false, defaultValue = "") String korisnickoIme,
    		@RequestParam(required = false, defaultValue = "") String prezime,
    		@RequestParam(required = false ) KorisnickaUloga uloga,
    		
    		@RequestParam(defaultValue="0") int pageNo) {
        Page<Korisnik> korisnici = korisnikService.find(eMail, ime, korisnickoIme, prezime,uloga, pageNo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total-Pages", Integer.toString(korisnici.getTotalPages()));

        
        return new ResponseEntity<>(toKorisnikDto.convert(korisnici.getContent()),headers, HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(value="/promenaLozinke/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody KorisnikPromenaLozinkeDto dto){
        // ova metoda se "okida" kada se primi PUT /korisnici?promenaLozinke
        // pogrešno bi bilo mapirati na npr. PUT /korisnici/lozinke, pošto "lozinka" nije punopravan REST resurs!

        if(!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(dto.getStaraLozinka());
        System.out.println(dto.getLozinka());
        System.out.println(dto.getPonovljenaLozinka());
        boolean rezultat;
        try {
            rezultat = korisnikService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(rezultat) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthKorisnikDto dto) {
        // Perform the authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            // Reload user details so we can generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id){
	  
		
	     
    	Korisnik k= korisnikService.findOneId(id);
	  

		
		
		
    	Korisnik korisnik = korisnikService.delete(id);
	 // Festival sacuvaniZadatak = porService.save(obrisanZadatak);
	    if(korisnik != null) { 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/subscribe/{id}")
    public ResponseEntity<KorisnikDTO> subscribe(
    		@RequestParam(required = false ) String iDpratioca,
    		@PathVariable Long id){
        Korisnik korisnik = korisnikService.findOneId(id);
        	System.out.println(iDpratioca);
        	korisnik.getPratioci().add(korisnikService.findOneId(Long.parseLong(iDpratioca)));
        	//video.getVlasnik().getPratioci2().add(video.getVlasnik());
        	Korisnik k2= korisnikService.findOneId(Long.parseLong(iDpratioca));
        	k2.getPratioci2().add(korisnik);
        	korisnikService.save(korisnik);
        	korisnikService.save(k2);
        	
        if(korisnik !=null) {
            return new ResponseEntity<>(toKorisnikDto.convert(korisnik), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/unsubscribe/{id}")
    public ResponseEntity<KorisnikDTO> unsubscribe(
    		@RequestParam(required = false ) String iDpratioca,
    		@PathVariable Long id){
    	 Korisnik korisnik = korisnikService.findOneId(id);
     	System.out.println(iDpratioca);
     	korisnik.getPratioci().remove(korisnikService.findOneId(Long.parseLong(iDpratioca)));
     	//video.getVlasnik().getPratioci2().add(video.getVlasnik());
     	Korisnik k2= korisnikService.findOneId(Long.parseLong(iDpratioca));
    	k2.getPratioci2().remove(korisnik);
     	
     	korisnikService.save(korisnik);
        	
        if(korisnik !=null) {
            return new ResponseEntity<>(toKorisnikDto.convert(korisnik), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
