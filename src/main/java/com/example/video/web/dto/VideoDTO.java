package com.example.video.web.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.video.enumeration.Vidljivost;
import com.example.video.model.Komentar;
import com.sun.istack.NotNull;

public class VideoDTO {
	
	private Long id;
	
	@NotNull
	private String Video;
	
	@NotNull
	private String Slicica;
	
	private String opis;
	
	private  String vidljivost;
	
	
	private Boolean dozvoljeniKomentari;
	
	private Boolean vidljivostRejtinga;
	
	private Boolean blokiran;
	
	private Integer brojLajkova;
	
	private Integer brojDislajkova;
	
	private Integer brojPregleda;
	
	private String datumKreiranja;
	
	private String imeVlasnika;
	
	private String prezimeVlasnika;
	
	private List<Long> idLajkova = new ArrayList<>();
	
	private List<Long> idDislajkova = new ArrayList<>();
	
	
	private List<Long> idLikeDislike = new ArrayList<>();
	
	
	  Map<Long, String> mapaLajkova = (Map<Long, String>) new HashMap();
	
	private Boolean korisnikBlokiran;
	
	private List<Long> idpratioca = new ArrayList<>();
	
	private Long korisnikId;
	
	private List<KomentarDTO> komentari= new ArrayList<>();
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVideo() {
		return Video;
	}

	public void setVideo(String video) {
		Video = video;
	}

	public String getSlicica() {
		return Slicica;
	}

	public void setSlicica(String slicica) {
		Slicica = slicica;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getVidljivost() {
		return vidljivost;
	}

	public void setVidljivost(String vidljivost) {
		this.vidljivost = vidljivost;
	}

	public Boolean getDozvoljeniKomentari() {
		return dozvoljeniKomentari;
	}

	public void setDozvoljeniKomentari(Boolean dozvoljeniKomentari) {
		this.dozvoljeniKomentari = dozvoljeniKomentari;
	}

	public Boolean getVidljivostRejtinga() {
		return vidljivostRejtinga;
	}

	public void setVidljivostRejtinga(Boolean vidljivostRejtinga) {
		this.vidljivostRejtinga = vidljivostRejtinga;
	}

	public Integer getBrojPregleda() {
		return brojPregleda;
	}

	public void setBrojPregleda(Integer brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public VideoDTO() {
		super();
	}

	public String getImeVlasnika() {
		return imeVlasnika;
	}

	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}

	public String getPrezimeVlasnika() {
		return prezimeVlasnika;
	}

	public void setPrezimeVlasnika(String prezimeVlasnika) {
		this.prezimeVlasnika = prezimeVlasnika;
	}

	public Boolean getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(Boolean blokiran) {
		this.blokiran = blokiran;
	}

	public Integer getBrojLajkova() {
		return brojLajkova;
	}

	public void setBrojLajkova(Integer brojLajkova) {
		this.brojLajkova = brojLajkova;
	}

	public Integer getBrojDislajkova() {
		return brojDislajkova;
	}

	public void setBrojDislajkova(Integer brojDislajkova) {
		this.brojDislajkova = brojDislajkova;
	}

	public List<KomentarDTO> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<KomentarDTO> komentari) {
		this.komentari = komentari;
	}

	public List<Long> getIdpratioca() {
		return idpratioca;
	}

	public void setIdpratioca(List<Long> idpratioca) {
		this.idpratioca = idpratioca;
	}

	public Boolean getKorisnikBlokiran() {
		return korisnikBlokiran;
	}

	public void setKorisnikBlokiran(Boolean korisnikBlokiran) {
		this.korisnikBlokiran = korisnikBlokiran;
	}

	public List<Long> getIdLajkova() {
		return idLajkova;
	}

	public void setIdLajkova(List<Long> idLajkova) {
		this.idLajkova = idLajkova;
	}

	public List<Long> getIdDislajkova() {
		return idDislajkova;
	}

	public void setIdDislajkova(List<Long> idDislajkova) {
		this.idDislajkova = idDislajkova;
	}

	public List<Long> getIdLikeDislike() {
		return idLikeDislike;
	}

	public void setIdLikeDislike(List<Long> idLikeDislike) {
		this.idLikeDislike = idLikeDislike;
	}


	public Map<Long, String> getMapaLajkova() {
		return mapaLajkova;
	}

	public void setMapaLajkova(Map<Long, String> mapaLajkova) {
		this.mapaLajkova = mapaLajkova;
	}
	
	
	
	
	
	

}
