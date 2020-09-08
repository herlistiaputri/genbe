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
	
	@PostMapping
	public StatusDto insertDataPend(@RequestParam (value = "idperson") Integer idPerson, @RequestBody List<PendidikanDto> pendidikanDto) {
		StatusDto statusDto = new StatusDto();
		if(personRepository.findById(idPerson).isPresent()) {
			pendidikanService.savePendidikan(pendidikanDto, idPerson);
			statusDto.setStatus(true);
			statusDto.setMessage("Data berhasil disimpan");
			return statusDto;
		} else 	{		
			statusDto.setStatus(false);
			statusDto.setMessage("Data gagal disimpan");
			return statusDto;
		}
	}
	
}