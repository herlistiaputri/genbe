
package com.shintadevi.genbe.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadevi.genbe.model.dto.DataDto;
import com.shintadevi.genbe.model.dto.DatalengkapDto;
import com.shintadevi.genbe.model.dto.StatusDto;
import com.shintadevi.genbe.model.dto.StatusdataDto;
import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;
import com.shintadevi.genbe.repository.PersonRepository;
import com.shintadevi.genbe.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private PersonService personService;
	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/data/person/{nik}")
	public List<Object> getDataPerson(@PathVariable String nik) {
		List<Object> object = new ArrayList<>();
		StatusDto status = new StatusDto();
		StatusdataDto statusData = new StatusdataDto();
		if(nik.length() == 16) {
			if(personRepository.findByNikLike(nik).isEmpty() == false) {
				Person person = personRepository.findByNikLike(nik).get(0);
				DatalengkapDto dto = convertToDto(person);
				statusData.setStatus("true");
				statusData.setMessage("success");
				statusData.setData(dto);
				object.add(statusData);
			} else {
				status.setStatus(false);
				status.setMessage("Data dengan nik " + nik + " tidak ditemukan");
				object.add(status);
			}
		} else {
			status.setStatus(false);
			status.setMessage("Jumlah digit nik tidak sama dengan 16");
			object.add(status);
		}
		return object;
	}
	
	@PostMapping("/save/data/person")
	public StatusDto insertDataPerson(@RequestBody DataDto dataDto) {
		StatusDto statusDto = new StatusDto();
		Person person = modelmapper.map(dataDto, Person.class);
		Biodata biodata = modelmapper.map(dataDto, Biodata.class);
		biodata.setIdPerson(person);
		personService.insertDataPerson(dataDto, biodata, person, statusDto);
		return statusDto;
	}
	
	private DatalengkapDto convertToDto(Person person) {
		DatalengkapDto datalengkapDto = new DatalengkapDto();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(person.getBiodata().getTanggalLahir());
		Integer umur = 	Year.now().getValue() - calendar.get(Calendar.YEAR);
		datalengkapDto.setNik(person.getNik());
		datalengkapDto.setNama(person.getNama());
		datalengkapDto.setAlamat(person.getAlamat());
		datalengkapDto.setNoHp(person.getBiodata().getNoHp());
		datalengkapDto.setTanggalLahir(person.getBiodata().getTanggalLahir());
		datalengkapDto.setTempatLahir(person.getBiodata().getTempatLahir());
		datalengkapDto.setUmur(Integer.toString(umur));
		datalengkapDto.setPendTerakhir(personRepository.getJenjangPend(person.getId()));
		
		return datalengkapDto;
	}
	
}
