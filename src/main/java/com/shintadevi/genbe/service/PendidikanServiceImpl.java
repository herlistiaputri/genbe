package com.shintadevi.genbe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shintadevi.genbe.model.entity.Pendidikan;
import com.shintadevi.genbe.repository.PendidikanRepository;

@Service
@Transactional
public class PendidikanServiceImpl implements PendidikanService{
	@Autowired
	private PendidikanRepository pendidikanRepository;

	@Override
	public Pendidikan saveDataPendidikan(Pendidikan pendidikan) {
		Pendidikan entity = pendidikanRepository.save(pendidikan);
		return pendidikanRepository.save(entity);
	}
	
	
}
