package com.shintadevi.genbe.model.dto;



public class PersonDto {

	private String nik;
	private String nama;
	private String alamat;
	private String noHp;
	private String tanggalLahir;
	private String tempatLahir;

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
	public String getNoHp() {
		return noHp;
	}
	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}
//	public Integer getIdBio() {
//		return idBio;
//	}
//	public void setIdBio(Integer idBio) {
//		this.idBio = idBio;
//	}

	public String getTempatLahir() {
		return tempatLahir;
	}
	public String getTanggalLahir() {
		return tanggalLahir;
	}
	public void setTanggalLahir(String tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

}
