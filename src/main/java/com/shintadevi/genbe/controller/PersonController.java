package com.shintadevi.genbe.controller;



import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadevi.genbe.model.dto.PersonDto;
import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.BiodataRepository;
import com.shintadevi.genbe.repository.PersonRepository;
import com.shintadevi.genbe.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {
	private final PersonRepository personRepository;
	private final BiodataRepository biodataRepository;


	@Autowired
	public PersonController(PersonRepository personRepository,BiodataRepository biodataRepository) {
		this.personRepository = personRepository;
		this.biodataRepository = biodataRepository;


	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping("/{nik}")
	public Person getByNik(@PathVariable String nik) {
		Person personList = personService.getDataByNik(nik);
		return personList;
	}
	
	@PostMapping("/savedata")
	public PersonDto insertDataPerson(@RequestBody PersonDto personDto) {
		
			Biodata biodata = modelMapper.map(personDto, Biodata.class);
			Person person = modelMapper.map(personDto, Person.class);
			biodata.setPerson(person);
			personService.saveBiodataToPerson(biodata);
			PersonDto personDtoDB = mapBiodataToPerson(biodata);
			
			return personDtoDB;
	}

	
	private PersonDto mapBiodataToPerson(Biodata biodata) {
		PersonDto personDto = modelMapper.map(biodata, PersonDto.class);
		modelMapper.map(biodata.getPerson(), personDto);
		return personDto;
	}
	
//	private StatusDto status(Boolean status, String message) {
//		StatusDto s = new StatusDto();
//		if(status == true) {
//			s.setStatus("true");
//			s.setMessage(message);
//		} else {
//			s.setStatus("false");
//			s.setMessage(message);
//		}
//		return s;		
//	}
	
}
