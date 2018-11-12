package cl.safe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.safe.dto.ExamenRequestDto;
import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.ExamenEntity;

@Service
public interface ExamenService {
	Long insertExamen(ExamenRequestDto examenRequestDto);
	List<ExamenEntity> findAll();
	List<ExamenEntity> getAllExamenesByConsultaId(Long consultaId);
}
