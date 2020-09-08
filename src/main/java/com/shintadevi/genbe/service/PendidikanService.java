package com.shintadevi.genbe.service;

import java.util.List;
import com.shintadevi.genbe.model.dto.PendidikanDto;

public interface PendidikanService {

	public List<PendidikanDto> savePendidikan(List<PendidikanDto> pendidikanDto, Integer idPerson);
}
