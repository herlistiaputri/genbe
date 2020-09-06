package com.shintadevi.genbe.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.shintadevi.genbe.model.dto.PendidikanDto;
import com.shintadevi.genbe.model.dto.StatusDto;
import com.shintadevi.genbe.model.entity.Pendidikan;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.PersonRepository;
import com.shintadevi.genbe.service.PendidikanService;

@RestController
@RequestMapping("/pendidikan")
public class PendidikanController {
	private final PersonRepository personRepository;
	
	@Autowired
	public PendidikanController (PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Autowired
	PendidikanService pendidikanService;
	
	@PostMapping("/save/data/pend")
	public StatusDto insertDataPend(@RequestParam Integer idPerson, @RequestBody List<PendidikanDto> pendDto) {
		StatusDto statusDto = new StatusDto();
		Pendidikan pendidikan = new Pendidikan();
		List<Pendidikan> pendList = new ArrayList<Pendidikan>();
		for (PendidikanDto pendidikanDto : pendDto) {
			pendidikan = convertToEntity(pendidikanDto);
			if(personRepository.findById(idPerson).isPresent()) {
				Person person = personRepository.findById(idPerson).get();
				pendidikan.setIdPerson(person);
			}
			pendidikanService.saveDataPendidikan(pendidikan);
			pendList.add(pendidikan);
		}
		statusDto.setStatus(true);
		statusDto.setMessage("Data berhasil disimpan");
		return statusDto;
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
