package com.example.video.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;


@Entity
public class Video {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 	@Column(nullable = false)
	 	private String Video;
	 	
	 	@Column(nullable = false)
	 	private String Slicica;
	 	
	 	@Column 
	 	private String opis;
	 	
	 	 //@Enumerated(EnumType.STRING)
	     private String vidljivost;
	 	 
	 	 
	 	 @Column
	 	 private Boolean dozvoljeniKomentari;
	 	 @Column
	 	 private Boolean vidljivostRejtinga;
	 	 
	 	 @Column
	 	 private Boolean blokiran;
	 	 
	 	 @Column
	 	 private Integer brojPregleda;
	 	 
	 	 
	 	 
	 	 
	 	 @Column
	 	 private LocalDate datumKreiranja;
	 	 
	 	 @ManyToOne
	 	 private Korisnik vlasnik;
	 	 
	 	 
	 	 @OneToMany(mappedBy = "video", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	 	 private List<LikeDislike> lajkovi = new ArrayList<>();
	 	 
	 	 @OneToMany(mappedBy = "video", fetch = FetchType.EAGER, cascade =CascadeType.DETACH)
	 	 private List<Komentar> komentari = new ArrayList<>();
	 	 
	 	@ElementCollection
	    @CollectionTable(name = "mapa", 
	      joinColumns = {@JoinColumn(name = "video_id", referencedColumnName = "id")})
	    @MapKeyColumn(name = "korisnik_id")
	    @Column(name = "like_dislike")
	 	 private Map<Korisnik, String> mapaLajkova = (Map<Korisnik, String>) new HashMap();

		public Video() {
			super();
		}

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

		public Boolean getVidljivostRejtinga() {
			return vidljivostRejtinga;
		}

		public void setVidljivostRejtinga(Boolean vidljivostRejtinga) {
			this.vidljivostRejtinga = vidljivostRejtinga;
		}

		public Boolean getBlokiran() {
			return blokiran;
		}

		public void setBlokiran(Boolean blokiran) {
			this.blokiran = blokiran;
		}

		public Integer getBrojPregleda() {
			return brojPregleda;
		}

		public void setBrojPregleda(Integer brojPregleda) {
			this.brojPregleda = brojPregleda;
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

		public List<LikeDislike> getLajkovi() {
			return lajkovi;
		}

		public void setLajkovi(List<LikeDislike> lajkovi) {
			this.lajkovi = lajkovi;
		}

		public List<Komentar> getKomentari() {
			return komentari;
		}

		public void setKomentari(List<Komentar> komentari) {
			this.komentari = komentari;
		}

		public Boolean getDozvoljeniKomentari() {
			return dozvoljeniKomentari;
		}

		public void setDozvoljeniKomentari(Boolean dozvoljeniKomentari) {
			this.dozvoljeniKomentari = dozvoljeniKomentari;
		}

		public Map<Korisnik, String> getMapaLajkova() {
			return mapaLajkova;
		}

		public void setMapaLajkova(Map<Korisnik, String> mapaLajkova) {
			this.mapaLajkova = mapaLajkova;
		}
		
		
	 	 
	 	
}
