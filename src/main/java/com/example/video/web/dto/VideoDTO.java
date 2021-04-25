package com.example.video.web.dto;

import com.example.video.enumeration.Vidljivost;
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
	
	private Integer brojPregleda;
	
	private String datumKreiranja;
	
	private Long korisnikId;

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
	
	
	
	
	
	

}
