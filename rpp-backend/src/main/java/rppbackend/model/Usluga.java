package rppbackend.model;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Entity implementation class for Entity: Usluga
 *
 */
@Entity
@Table (name="Usluga", schema="public")
@NamedQuery(name="Usluga.findAll", query="SELECT u FROM Usluga u")

public class Usluga implements Serializable {

	   
	@Id
	@SequenceGenerator(name="USLUGA_ID_GENERATOR", sequenceName="USLUGA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USLUGA_ID_GENERATOR")
	private Integer id;
	private String naziv;
	private String opis_usluge;
	private Date datum_ugovora;
	private Integer provizija;
	
	@ManyToOne
	@JoinColumn(name="filijala")
	private Filijala filijala;
	
	@ManyToOne
	@JoinColumn(name="korisnik")
	private KorisnikUsluge korisnik;
	private static final long serialVersionUID = 1L;

	public Usluga() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}   
	public String getOpis_usluge() {
		return this.opis_usluge;
	}

	public void setOpis_usluge(String opis_usluge) {
		this.opis_usluge = opis_usluge;
	}   
	public Date getDatum_ugovora() {
		return this.datum_ugovora;
	}

	public void setDatum_ugovora(Date datum_ugovora) {
		this.datum_ugovora = datum_ugovora;
	}   
	public Integer getProvizija() {
		return this.provizija;
	}

	public void setProvizija(Integer provizija) {
		this.provizija = provizija;
	}   
	public Filijala getFilijala() {
		return this.filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}   
	public KorisnikUsluge getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnikUsluge(KorisnikUsluge korisnik) {
		this.korisnik = korisnik;
	}
   
}
