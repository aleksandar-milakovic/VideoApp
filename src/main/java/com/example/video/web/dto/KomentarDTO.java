package com.example.video.web.dto;

public class KomentarDTO {
	
	private Long id;
	
	private String sadrzaj;
	
	private String datumKreiranja;
	
	private Long vlasnikId;
	
	private Long videoId;
	
	private Integer brojLajkova;
	
	private Integer brojDislajkova;
	
	
	private String imeVlasnika;
	
	private String prezimeVlasnika;

	public KomentarDTO() {
		super();
	}

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

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Long getVlasnikId() {
		return vlasnikId;
	}

	public void setVlasnikId(Long vlasnikId) {
		this.vlasnikId = vlasnikId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
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
	
	
}
