package cl.safe.service;

import java.util.List;

import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.ExamenEntity;
import cl.safe.entity.RiesgoEntity;

public interface RiesgoService {
	Long insertRiesgo(RiesgoRequestDto riesgoRequestDto);
	List<RiesgoEntity> findAll();
}
