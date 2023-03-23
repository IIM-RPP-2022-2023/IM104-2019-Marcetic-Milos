package model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Banka
 *
 */
@Entity
@Table(name="Banka", schema="public")
@NamedQuery(name="Banka.findAll", query="SELECT b FROM Banka b")

public class Banka implements Serializable {

	   
	@Id
	@SequenceGenerator(name="BANKA_ID_GENERATOR", sequenceName="BANKA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANKA_ID_GENERATOR")
	private Integer id;
	private String naziv;
	private String kontakt;
	private Integer pib;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="banka")
	@JsonIgnore
	private List<Filijala> filijala;

	public Banka() {
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
	public String getKontakt() {
		return this.kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}   
	public Integer getPib() {
		return this.pib;
	}

	public void setPib(Integer pib) {
		this.pib = pib;
	}
   
}
