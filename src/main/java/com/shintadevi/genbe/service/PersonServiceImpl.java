package com.shintadevi.genbe.service;

import java.time.Year;
import java.util.Calendar;


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

			return null;}
		if((Year.now().getValue() - calendar.get(Calendar.YEAR)) < 30) {

			return null;}
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return biodata;
	}
	
	@Override
	public Person getDataByNik(String nik) {
		if(nik.length() != 16) {

			return null;
		} 

		Person personList = personRepository.ambilDataByNik(nik);
		return personList;
	}

	
	
}
