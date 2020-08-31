package com.shintadevi.genbe.service;

import java.time.Year;
import java.util.Calendar;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintadevi.genbe.model.dto.DataDto;
import com.shintadevi.genbe.model.dto.StatusDto;
import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.BiodataRepository;
import com.shintadevi.genbe.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	BiodataRepository biodataRepository;
//	@Autowired
//	StatusDto statusDto;
	
	@Override
	public StatusDto insertDataPerson(DataDto dataDto, Biodata biodata, Person person, StatusDto statusDto) {	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(biodata.getTanggalLahir());
		if(dataDto.getNik().length() != 16) {
			statusDto.setStatus(false);
			statusDto.setMessage("Jumlah digit nik harus 16");
			System.out.println("nik salah");
			return statusDto;
		}
		if((Year.now().getValue() - calendar.get(Calendar.YEAR)) < 30) {
			statusDto.setStatus(false);
			statusDto.setMessage("Usia anda belum memenuhi");
			System.out.println("usia salah");
			return statusDto;
		}
		personRepository.save(person);
		personRepository.save(biodata.getIdPerson());
		biodataRepository.save(biodata);
		statusDto.setStatus(true);
		statusDto.setMessage("Data berhasil disimpan");
		System.out.println("berhasil");
		return statusDto;
	}
}
