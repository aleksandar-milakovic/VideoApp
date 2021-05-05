package com.example.video.web.dto;

import java.util.ArrayList;
import java.util.List;

public class KorisnikDTO {

   // @Positive(message = "Id mora biti pozitivan broj.")
    private Long id;

    //@NotBlank
    private String korisnickoIme;

    //@NotEmpty
    //@Email
    private String eMail;

    //@Size(min=3, max=50)
    private String ime;

    //@Size(min=3, max=50)
    private String prezime;
    
    
    private String uloga;
    
    
    private String opis;
    
    private Boolean blokiran;
    
    
    private Integer brojPratilaca;
    
    private List<VideoDTO> videi = new ArrayList<>();

    private String datumKreiranja;
    
    private String lozinka;

	private List<Long> idpratioca = new ArrayList<>();
	
	private List<KorisnikDTO> pratioci = new ArrayList<>();
	
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Boolean getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(Boolean blokiran) {
		this.blokiran = blokiran;
	}

	public Integer getBrojPratilaca() {
		return brojPratilaca;
	}

	public void setBrojPratilaca(Integer brojPratilaca) {
		this.brojPratilaca = brojPratilaca;
	}

	public List<VideoDTO> getVidei() {
		return videi;
	}

	public void setVidei(List<VideoDTO> videi) {
		this.videi = videi;
	}

	public List<Long> getIdpratioca() {
		return idpratioca;
	}

	public void setIdpratioca(List<Long> idpratioca) {
		this.idpratioca = idpratioca;
	}

	public List<KorisnikDTO> getPratioci() {
		return pratioci;
	}

	public void setPratioci(List<KorisnikDTO> pratioci) {
		this.pratioci = pratioci;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
	
   
}
