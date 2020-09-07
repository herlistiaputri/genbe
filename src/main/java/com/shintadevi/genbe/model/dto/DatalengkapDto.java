package com.shintadevi.genbe.model.dto;

import java.sql.Date;

public class DatalengkapDto {
	private String nik;
	private String nama;
	private String alamat;
	private String noHp;
	private Date tanggalLahir;
	private String tempatLahir;
	private String umur;
	private String pendTerakhir;
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
	public String getUmur() {
		return umur;
	}
	public void setUmur(String umur) {
		this.umur = umur;
	}
	public String getPendTerakhir() {
		return pendTerakhir;
	}
	public void setPendTerakhir(String pendTerakhir) {
		this.pendTerakhir = pendTerakhir;
	}
	

}