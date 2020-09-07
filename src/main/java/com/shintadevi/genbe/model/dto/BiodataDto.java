package com.shintadevi.genbe.model.dto;

import java.sql.Date;

import com.shintadevi.genbe.model.entity.Person;

public class BiodataDto {
	private Integer id;
	private String noHp;
	private Date tanggalLahir;
	private String tempatLahir;
	private Person idPerson;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Person getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(Person idPerson) {
		this.idPerson = idPerson;
	}
	
}