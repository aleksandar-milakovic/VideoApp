package com.example.video.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.video.enumeration.KorisnickaUloga;




@Entity
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String korisnickoIme;

    @Column( unique = true, nullable = false)
    private String eMail;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(nullable = false)
    private String lozinka;

    @Enumerated(EnumType.STRING)
    private KorisnickaUloga uloga;
    
    @Column
    private String opisKanala;
    
    @Column
    private Boolean blokiran;
   
    @ManyToMany
    @JoinTable(name = "korisnik_pratioci", joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pratilac_id", referencedColumnName = "id"))
    private List<Korisnik> pratioci = new ArrayList<>();
    
    @Column
    private LocalDate datumReg;
    
    

	 @OneToMany(mappedBy = "vlasnik", fetch = FetchType.LAZY, cascade =CascadeType.DETACH)
	 private List<Komentar> komentari = new ArrayList<>();
   
	 
	 @OneToMany(mappedBy = "vlasnik", fetch = FetchType.LAZY, cascade =CascadeType.DETACH)
	 private List<Video> videi = new ArrayList<>();
	 
    public Korisnik(){

    }

    public Korisnik(String korisnickoIme, String eMail, String ime, String prezime, String lozinka, KorisnickaUloga uloga) {
        this.korisnickoIme = korisnickoIme;
        this.eMail = eMail;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public KorisnickaUloga getUloga() {
        return uloga;
    }

    public void setUloga(KorisnickaUloga uloga) {
        this.uloga = uloga;
    }

   

    public String getOpisKanala() {
		return opisKanala;
	}

	public void setOpisKanala(String opisKanala) {
		this.opisKanala = opisKanala;
	}

	public Boolean getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(Boolean blokiran) {
		this.blokiran = blokiran;
	}

	public List<Korisnik> getPratioci() {
		return pratioci;
	}

	public void setPratioci(List<Korisnik> pratioci) {
		this.pratioci = pratioci;
	}

	public LocalDate getDatumReg() {
		return datumReg;
	}

	public void setDatumReg(LocalDate datumReg) {
		this.datumReg = datumReg;
	}
	
	

	public List<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public List<Video> getVidei() {
		return videi;
	}

	public void setVidei(List<Video> videi) {
		this.videi = videi;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Korisnik other = (Korisnik) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + "]";
    }

}
