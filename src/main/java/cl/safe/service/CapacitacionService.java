package cl.safe.service;

import java.util.Date;
import java.util.List;

import cl.safe.dto.CapacitacionRequestDto;
import cl.safe.dto.PaginacionObjetoResponseDto;
import cl.safe.entity.AsistenciaTrabajadorEntity;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.VisitaMedicaEntity;
public interface CapacitacionService {
	public CapacitacionEntity findOneSP(Long id); 
	public List<CapacitacionEntity> findAllByEmpresaExaminadorSP(Long empresaId, Long examinadorId);
	public List<CapacitacionEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long supervisorId);

	public List<AsistenciaTrabajadorEntity> findAllAsistentesByCapacitacionId(Long capacitacionId);
	Long crearCapacitacion(CapacitacionRequestDto capacitacionEntity);
	Long cerrarCapacitacion(Long capacitacionId);
	
	
	PaginacionObjetoResponseDto<CapacitacionEntity> getCapacitacionesADMINEMPRESA_PAG(Long empresa, Long pageNumber, Long pageSize, Date fromDate, Date toDate);
	
}
