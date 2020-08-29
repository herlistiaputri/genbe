package com.shintadevi.genbe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadevi.genbe.model.dto.PendidikanDto;
import com.shintadevi.genbe.model.entity.Pendidikan;
import com.shintadevi.genbe.repository.PendidikanRepository;
import com.shintadevi.genbe.repository.PersonRepository;
import com.shintadevi.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class PendidikanController {
	private final PendidikanRepository pendidikanRepository;
	private final PersonRepository personRepository;

	@Autowired
	public PendidikanController (PendidikanRepository pendidikanRepository, PersonRepository personRepository) {
		this.pendidikanRepository = pendidikanRepository;
		this.personRepository = personRepository;
	}
	
	@Autowired
	private PendidikanService pendidikanService;
	
	@GetMapping
	public List<PendidikanDto> get(){
		List<Pendidikan> pendList = pendidikanRepository.findAll();
		List<PendidikanDto> pendDtoList = pendList.stream().map(this::convertToDto)
				.collect(Collectors.toList());
		return pendDtoList;
	}
	
	@PostMapping("/input/datapendidikan/")
	public List<PendidikanDto> insert(@RequestBody List<PendidikanDto> dto) {
		List<Pendidikan> pendidikanList = new ArrayList<Pendidikan>();
		for (PendidikanDto pendidikanDto : dto) {
			Pendidikan pendidikan = convertToEntity(pendidikanDto);
			pendidikanService.insertDataPendidikan(pendidikan);
			pendidikanList.add(pendidikan);
		}
		List<PendidikanDto> pendidikanDtoList = pendidikanList.stream()
				.map(this::convertToDto).collect(Collectors.toList());
		return pendidikanDtoList;
		
	}
	
	private Pendidikan convertToEntity(PendidikanDto dto) {
		Pendidikan entity = new Pendidikan();
		entity.setPerson(personRepository.getOne(dto.getIdPerson()));
		entity.setJenjang(dto.getJenjang());
		entity.setInstitusi(dto.getInstitusi());
		entity.setTahunMasuk(dto.getTahunMasuk());
		entity.setTahunLulus(dto.getTahunLulus());
		return entity;
	}
	
	private PendidikanDto convertToDto(Pendidikan entity) {
		PendidikanDto dto = new PendidikanDto();
		dto.setIdPerson(entity.getPerson().getIdPerson());
		dto.setJenjang(entity.getJenjang());
		dto.setInstitusi(entity.getInstitusi());
		dto.setTahunMasuk(entity.getTahunMasuk());
		dto.setTahunLulus(entity.getTahunLulus());
		return dto;
	}
	
}
