package com.shintadevi.genbe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shintadevi.genbe.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	@Query (value = "Select * From t_person p Left Join t_biodata b ON p.id_person = b.idperson Where nik = ?1", nativeQuery = true )
	public Person ambilDataByNik(String nik);
}
