package com.shintadevi.genbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shintadevi.genbe.model.entity.Pendidikan;

public interface PendidikanRepository extends JpaRepository<Pendidikan, Integer>{
	List<Pendidikan> findAllByIdPerson(Integer idPerson);
}
