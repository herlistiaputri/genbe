package com.shintadevi.genbe.service;


import com.shintadevi.genbe.model.entity.Biodata;
import com.shintadevi.genbe.model.entity.Person;

public interface PersonService {
	public Biodata saveBiodataToPerson(Biodata biodata) ;
	public Person getDataByNik(String nik);

}
