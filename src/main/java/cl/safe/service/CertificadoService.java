package cl.safe.service;

import java.util.List;

import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.dto.ConsultaMedicaTrabajadorResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;
import cl.safe.entity.CapacitacionPorHacerTrabajadorEntity;
import cl.safe.entity.ConsultaMedicaFullTrabajadorEntity;
import cl.safe.entity.ConsultaMedicaTrabajadorEntity;

public interface CertificadoService {
	List<CapacitacionParaTrabajadorResponseDto> getAllCetificados(String run, Long empresaId);
	CapacitacionParaTrabajadorEntity getCetificado(String run, Long empresaId, Long asistenciaId);
	
	List<ConsultaMedicaTrabajadorResponseDto> getAllConsultas(String run, Long empresaId);
	
	ConsultaMedicaFullTrabajadorEntity getConsultas(String run, Long empresaId, Long consultaId);
	
	List<CapacitacionPorHacerTrabajadorEntity> getAllCapacitacionesPorHacer(String run, Long empresaId);
}
