package com.shintadevi.genbe.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_person")
public class Person {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Integer id;
	@Column(name = "nik", length = 16, unique = true)
	private String nik;
	@Column(name = "nama", length = 50)
	private String nama;
	@Column(name = "alamat")
	private String alamat;
	
	@OneToOne(mappedBy = "idPerson")
	private Biodata biodata;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public Biodata getBiodata() {
		return biodata;
	}
	public void setBiodata(Biodata biodata) {
		this.biodata = biodata;
	}	
	
}

