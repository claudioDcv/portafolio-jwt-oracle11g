package cl.safe.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.safe.dto.ConsultaMedicaRequestDto;
import cl.safe.dto.PaginacionObjetoResponseDto;
import cl.safe.dto.VisitaMedicaRequestDto;
import cl.safe.entity.ConsultaMedicaEntity;
import cl.safe.entity.VisitaMedicaEntity;

@Service
public interface VisitaMedicaService {
	List<VisitaMedicaEntity> findAll();
	public List<VisitaMedicaEntity> findAllByEmpresaMedicoConfirmacionSP(Long empresaId, Long medicoId, Integer confirmacion);
	public List<VisitaMedicaEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long superVisorId);
	
	Long crearVisitaMedica(VisitaMedicaRequestDto visitaMedicaRequestDto);
	Long cerrarVisitaMedica(Long visitaMedicaId);
	Long crearConsultaMedica(ConsultaMedicaRequestDto consultaMedicaRequestDto);
	Long cerrarConsultaMedica(Long consultaMedicaId);
	Long asignarExamenConsultaMedica(Long consultaMedicaid, Long examenId);
	Long aceptarVisitaMedica(Long visitaMedicaId);
	Long rechazarVisitaMedica(Long visitaMedicaId);
	VisitaMedicaEntity getVisitaMedicaById(Long empresaId, Long visitaMedicaId);
	List<ConsultaMedicaEntity> getAllConsultasMedicasByVisitaMedica(Long visitaMedicaId);
	
	PaginacionObjetoResponseDto<VisitaMedicaEntity> getVisitaMedicaADMINEMPRESA_PAG(Long empresa, Long pageNumber, Long pageSize, Date fromDate, Date toDate);
}
