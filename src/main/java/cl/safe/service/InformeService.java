package cl.safe.service;

import java.util.Date;
import java.util.List;

import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.InformeTrabajadorDto;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.dto.PaginacionObjetoResponseDto;
import cl.safe.dto.ObservacionRequestDto;
import cl.safe.entity.ObservacionEntity;
public interface InformeService {
	public Long creacionInformeTrabajador(InformeTrabajadorRequestDto informeTrabajadorDto);
	public Long creacionInformeInstalacion(InformeInstalacionDtoRequest informeInstalacionDto);
	InformeInstalacionDto getInformeInstalacionById(Long id);
	InformeTrabajadorDto getInformeTrabajadorById(Long id);
	List<ObservacionEntity> getObservacionByInformeDetallerId(Long id);
	Long creacionObservacionConInformeId(ObservacionRequestDto observacionRequestDto);
	List<InformeTrabajadorDto> getAllInformeTrabajadorByEstado(Long id, Long empresa, Long estado);
	List<InformeInstalacionDto> getAllInformeInstalacionyEstado(Long id, Long empresa, Long estado);
	Boolean solicitarRevisionInformeDetalle(Long informeDetalleId, Long tecnicoId);
	
	List<InformeTrabajadorDto> getAllInformeTrabajadorBySupervisorId(Long supervisorId, Long idEmpresa);
	List<InformeInstalacionDto> getAllInformeInstalacionyBySupervisorId(Long supervisorId, Long idEmpresa);
	
	Long asignarPrevencionista(Long informeDetalleId, Long prevencionistaId);
	
	Long rechazarInformeDetalle(Long informeDetalleId);
	Long aprobarInformeDetalle(Long informeDetalleId);
	
	List<InformeTrabajadorDto> getAllInformeTrabajadorByEstadoPrevencionistaId(Long preveId, Long idEmpresa, Long estado);
	List<InformeInstalacionDto> getAllInformeInstalacionyByEstadoPrevencionistaId(Long preveId, Long idEmpresa, Long estado);

	Long agregarRecomendacionParaObservacionPorPreve(String observacion, Long observacionId);
	
	// solo admin empresa
	List<InformeInstalacionDto> getAllInformeInstalacionADMINEMPRESA(Long empresa);
	List<InformeTrabajadorDto> getAllInformeTrabajadorADMINEMPRESA(Long empresa);
	
	PaginacionObjetoResponseDto getAllInformeTrabajadorADMINEMPRESA_PAG(Long empresa, Long pageNumber, Long pageSize, Date fromDate, Date toDate);
	PaginacionObjetoResponseDto getAllInformeInstalacionADMINEMPRESA_PAG(Long empresa, Long pageNumber, Long pageSize, Date fromDate, Date toDate);
}
