package com.shintadevi.genbe.service;


import com.shintadevi.genbe.model.dto.DataDto;
import com.shintadevi.genbe.model.dto.StatusDto;
import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;

public interface PersonService {
	public StatusDto insertDataPerson(DataDto dataDto, Biodata biodata, Person person, StatusDto statusDto);
}
