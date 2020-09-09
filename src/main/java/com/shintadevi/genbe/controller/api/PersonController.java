
package com.shintadevi.genbe.controller.api;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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

	@GetMapping("/data/{nik}")
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
	
	//Get for all data + umur and pend terakhir
	@GetMapping("/data")
	public List<DatalengkapDto> getDataLengkap(){
		List<Person> personList = personRepository.findAll();
		List<DatalengkapDto> dataDto = personList.stream()
							.map(person -> mapDataToDatalengkapDto(person))
							.collect(Collectors.toList());
		return dataDto;
	}
	
	private DatalengkapDto mapDataToDatalengkapDto(Person person) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(person.getBiodata().getTanggalLahir());
		Integer umur = 	Year.now().getValue() - calendar.get(Calendar.YEAR);
		DatalengkapDto dataDto = modelmapper.map(person, DatalengkapDto.class);
		modelmapper.map(person.getBiodata(), dataDto);
		dataDto.setUmur(Integer.toString(umur));
		dataDto.setPendTerakhir(personRepository.getJenjangPend(person.getId()));
		return dataDto;
	}

	//Get for edit data per id person
	@GetMapping("/{idperson}")
	public DataDto getBiodata(@PathVariable (value = "idperson") Integer id) {
		Person person = personRepository.findById(id).get();
		DataDto dataDto = new DataDto();
		dataDto.setId(person.getId());
		dataDto.setNik(person.getNik());
		dataDto.setNama(person.getNama());
		dataDto.setAlamat(person.getAlamat());
		dataDto.setTempatLahir(person.getBiodata().getTempatLahir());
		dataDto.setTanggalLahir(person.getBiodata().getTanggalLahir());
		dataDto.setNoHp(person.getBiodata().getNoHp());
		return dataDto;
	}
	
	//Get data person after input
	@GetMapping
	public List<DataDto> getData() {
		List<Person> personList = personRepository.findAll();
		List<DataDto> dataDto = personList.stream()
									.map(person -> mapDataToDataDto(person))
									.collect(Collectors.toList());
		return dataDto;
	}
	
	private DataDto mapDataToDataDto(Person person) {
		DataDto dataDto = modelmapper.map(person, DataDto.class);
		modelmapper.map(person.getBiodata(), dataDto);
		dataDto.setId(person.getId());
		return dataDto;
	}
	
	@PostMapping
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
