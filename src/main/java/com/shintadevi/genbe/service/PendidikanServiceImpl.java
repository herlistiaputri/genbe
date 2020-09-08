package com.shintadevi.genbe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintadevi.genbe.model.dto.PendidikanDto;
import com.shintadevi.genbe.model.entity.Pendidikan;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.PendidikanRepository;
import com.shintadevi.genbe.repository.PersonRepository;

@Service
@Transactional
public class PendidikanServiceImpl implements PendidikanService{
	@Autowired
	private PendidikanRepository pendidikanRepository;
	@Autowired
	private PersonRepository personRepository;


	@Override
	public List<PendidikanDto> savePendidikan(List<PendidikanDto> pendidikanDto, Integer idPerson) {
		for (PendidikanDto dto : pendidikanDto) {
			Pendidikan pendidikan = convertToEntity(dto);
			Person person = personRepository.findById(idPerson).get();
			pendidikan.setIdPerson(person);
			pendidikanRepository.save(pendidikan);
		}
		return pendidikanDto;
	}
	
	private Pendidikan convertToEntity( PendidikanDto dto) {
		Pendidikan entity = new Pendidikan();
		entity.setJenjang(dto.getJenjang());
		entity.setInstitusi(dto.getInstitusi());
		entity.setTahunMasuk(dto.getTahunMasuk());
		entity.setTahunLulus(dto.getTahunLulus());
		return entity;		
	}
	
	
}
