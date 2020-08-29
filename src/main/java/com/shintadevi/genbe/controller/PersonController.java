package com.shintadevi.genbe.controller;

import java.time.Year;
import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadevi.genbe.model.dto.PersonDto;
import com.shintadevi.genbe.model.dto.StatusDto;
import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.BiodataRepository;
import com.shintadevi.genbe.repository.PersonRepository;
import com.shintadevi.genbe.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController{
	private final BiodataRepository biodataRepository;
	private final PersonRepository personRepository;
	
	public PersonController(BiodataRepository biodataRepository, PersonRepository personRepository) {
		this.biodataRepository = biodataRepository;
		this.personRepository = personRepository;
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
//	public PersonDto insertDataPerson(@RequestBody PersonDto personDto) {	
//			Biodata biodata = modelMapper.map(personDto, Biodata.class);
//			Person person = modelMapper.map(personDto, Person.class);
//			biodata.setPerson(person);
//			personService.saveBiodataToPerson(biodata);
//			PersonDto personDtoDB = mapBiodataToPerson(biodata);
//			return personDtoDB;
//	}
/*Post data dengan Status message*/
	public StatusDto insertDataPerson(@RequestBody PersonDto personDto) {
		Biodata biodata = modelMapper.map(personDto, Biodata.class);
		Person person = modelMapper.map(personDto, Person.class);
		biodata.setPerson(person);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(biodata.getTanggalLahir());
		if(biodata.getPerson().getNik().length() != 16) {
			return status(false,"Jumlah digit nik harus 16");
		}
		if((Year.now().getValue() - calendar.get(Calendar.YEAR)) < 30) {
			return status(false,"Usia anda belum memenuhi");
		}
		personRepository.save(biodata.getPerson());
		biodataRepository.save(biodata);
		return status(true,"Succsess");
	}

	private PersonDto mapBiodataToPerson(Biodata biodata) {
		PersonDto personDto = modelMapper.map(biodata, PersonDto.class);
		modelMapper.map(biodata.getPerson(), personDto);
		return personDto;
	}
	
	private StatusDto status(Boolean bool, String message) {
		StatusDto statusDto = new StatusDto();
		if(bool == true) {
			statusDto.setStatus("true");
			statusDto.setMessage(message);
		} else {
			statusDto.setStatus("false");
			statusDto.setMessage(message);
		}
		return statusDto;
	}
}
