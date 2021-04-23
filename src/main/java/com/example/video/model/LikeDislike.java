package com.example.video.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class LikeDislike {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 	
	 	@Column
	 	private Boolean isitLike;
	 	
	 	@Column 
	 	private LocalDate datumKreiranja;
	 	
	 	@ManyToOne
	 	private Video video;
	 	
	 	@ManyToOne
	 	private Komentar komentar;

		public LikeDislike() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Boolean getIsitLike() {
			return isitLike;
		}

		public void setIsitLike(Boolean isitLike) {
			this.isitLike = isitLike;
		}

		public LocalDate getDatumKreiranja() {
			return datumKreiranja;
		}

		public void setDatumKreiranja(LocalDate datumKreiranja) {
			this.datumKreiranja = datumKreiranja;
		}

		public Video getVideo() {
			return video;
		}

		public void setVideo(Video video) {
			this.video = video;
		}

		public Komentar getKomentar() {
			return komentar;
		}

		public void setKomentar(Komentar komentar) {
			this.komentar = komentar;
		}
	 	
	 	
}
