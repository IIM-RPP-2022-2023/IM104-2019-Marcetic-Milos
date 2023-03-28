package rppbackend.model;


import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Filijala
 *
 */
@Entity
@Table(name="Filijala", schema="public")
@NamedQuery(name="Filijala.findAll", query="SELECT f FROM Filijala f")
public class Filijala implements Serializable {

	   
	@Id
	@SequenceGenerator(name="FILIJALA_ID_GENERATOR", sequenceName="FILIJALA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILIJALA_ID_GENERATOR")
	private Integer id;
	private String adresa;
	private Integer broj_pultova;
	private Boolean poseduje_sef;
	
	@ManyToOne
	@JoinColumn(name="banka")
	private Banka banka;
	private static final long serialVersionUID = 1L;

	
	@OneToMany(mappedBy="filijala") 
	@JsonIgnore
	private List<Usluga> usluga;
	public Filijala() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}   
	public Integer getBroj_pultova() {
		return this.broj_pultova;
	}

	public void setBroj_pultova(Integer broj_pultova) {
		this.broj_pultova = broj_pultova;
	}   
	public Boolean getPoseduje_sef() {
		return this.poseduje_sef;
	}

	public void setPoseduje_sef(Boolean poseduje_sef) {
		this.poseduje_sef = poseduje_sef;
	}   
	public Banka getBanka() {
		return this.banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}
   
}
