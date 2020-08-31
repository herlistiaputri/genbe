package com.shintadevi.genbe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shintadevi.genbe.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
	@Query(value = "SELECT jenjang FROM public.t_pendidikan where id_person = ?1 order by tahunlulus desc limit 1", nativeQuery = true)
	public String getJenjangPend(Integer idPerson);
	public List<Person> findByNikLike(String nik);
}
