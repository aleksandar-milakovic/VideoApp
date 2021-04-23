package com.example.video.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Komentar {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String sadrzaj;
	
	@Column
	private LocalDate datumKreiranja;
	
	@ManyToOne
	private Korisnik vlasnik;
	
	@ManyToOne
	private Video video;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public LocalDate getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(LocalDate datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	
	
}
