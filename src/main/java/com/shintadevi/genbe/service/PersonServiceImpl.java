package com.shintadevi.genbe.service;

import java.time.Year;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.BiodataRepository;
import com.shintadevi.genbe.repository.PersonRepository;

@Transactional
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BiodataRepository biodataRepository;
	
	@Override
	//kondisi berfungsi tapi masih ada respon input yg ditampilkan, hanya untuk ke database tidak masuk
	public Biodata saveBiodataToPerson(Biodata biodata) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(biodata.getTanggalLahir());
		if(biodata.getPerson().getNik().length() != 16) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return null;}
		if((Year.now().getValue() - calendar.get(Calendar.YEAR)) < 30) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "Data gagal masuk, usia harus diatas 30 tahun");
			return null;}
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return biodata;
	}
	
	

	@Override
	public Person getDataByNik(String nik) {
		if(nik.length() != 16) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "data gagal masuk, jumlah digit nik tidak sama dengan 16");
			return null;
		} 

		Person personList = personRepository.ambilDataByNik(nik);
		return personList;
	}

	
	
}
