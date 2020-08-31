package com.shintadevi.genbe.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pendidikan")
public class Pendidikan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pendidikan")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idPerson", nullable = false)
	private Person idPerson;
	@Column(name = "jenjang", length = 10, nullable = false)
	private String jenjang;
	@Column(name = "institusi", length = 50, nullable = false )
	private String institusi;
	@Column(name = "tahunmasuk", length = 10, nullable = false)
	private String tahunMasuk;
	@Column(name = "tahunlulus", length = 10, nullable = false)
	private String tahunLulus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Person getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Person idPerson) {
		this.idPerson = idPerson;
	}
	public String getJenjang() {
		return jenjang;
	}
	public void setJenjang(String jenjang) {
		this.jenjang = jenjang;
	}
	public String getInstitusi() {
		return institusi;
	}
	public void setInstitusi(String institusi) {
		this.institusi = institusi;
	}
	public String getTahunMasuk() {
		return tahunMasuk;
	}
	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}
	public String getTahunLulus() {
		return tahunLulus;
	}
	public void setTahunLulus(String tahunLulus) {
		this.tahunLulus = tahunLulus;
	}
}