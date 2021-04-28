package com.example.video.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	
	@OneToMany(mappedBy = "komentar", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	 private List<LikeDislike> lajkovi = new ArrayList<>();

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

	public List<LikeDislike> getLajkovi() {
		return lajkovi;
	}

	public void setLajkovi(List<LikeDislike> lajkovi) {
		this.lajkovi = lajkovi;
	}
	
	
	
}
