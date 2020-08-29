package com.shintadevi.genbe.model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
//@Data
@Table(name = "t_Biodata")
public class Biodata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Bio")
	private Integer idBio;
	@Column(name = "no_Hp", length = 16)
	private String noHp;
	@Column(name = "tanggal_lahir", length = 15)
	private Date tanggalLahir;
	@Column(name = "tempat_lahir", length = 50)
	private String tempatLahir;
	
	@OneToOne
	@JoinColumn(name = "idperson", nullable = false)
	private Person person;

	public Integer getIdBio() {
		return idBio;
	}

	public void setIdBio(Integer idBio) {
		this.idBio = idBio;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
