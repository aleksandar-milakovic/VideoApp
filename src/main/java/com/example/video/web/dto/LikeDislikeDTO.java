package com.example.video.web.dto;

public class LikeDislikeDTO {
	
	private Long id;
	
	private Boolean isitLike;
	
	private String datumKreiranja;
	
	private Long videoId;
	
	private Long komentarId;

	public LikeDislikeDTO() {
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

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getKomentarId() {
		return komentarId;
	}

	public void setKomentarId(Long komentarId) {
		this.komentarId = komentarId;
	}
	
	
	
	
	

}
