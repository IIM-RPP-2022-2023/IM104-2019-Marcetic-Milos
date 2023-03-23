package model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * Entity implementation class for Entity: Korisnik_usluge
 *
 */
@Entity

@Table(name = "korisnik_usluge", schema = "public")
@NamedQuery(name="KorisnikUsluge.findAll", query="SELECT k FROM KorisnikUsluge k")

public class KorisnikUsluge implements Serializable {

	   
	@Id
	@SequenceGenerator(name="KORISNIK_USLUGE_ID_GENERATOR", sequenceName="KORISNIK_USLUGE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORISNIK_USLUGE_ID_GENERATOR")
	private Integer id;
	private String ime;
	private String prezime;
	private String maticni_broj;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="korisnik")
	@JsonIgnore
	private List<Usluga> usluga;

	public KorisnikUsluge() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}   
	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}   
	public String getMaticni_broj() {
		return this.maticni_broj;
	}

	public void setMaticni_broj(String maticni_broj) {
		this.maticni_broj = maticni_broj;
	}
   
}
