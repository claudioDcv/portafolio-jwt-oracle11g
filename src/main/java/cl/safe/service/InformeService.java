package cl.safe.service;

import java.util.List;

import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.InformeTrabajadorDto;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.dto.ObservacionRequestDto;
import cl.safe.entity.ObservacionEntity;
public interface InformeService {
	public Long creacionInformeTrabajador(InformeTrabajadorRequestDto informeTrabajadorDto);
	public Long creacionInformeInstalacion(InformeInstalacionDtoRequest informeInstalacionDto);
	InformeInstalacionDto getInformeInstalacionById(Long id);
	List<ObservacionEntity> getObservacionByInformeDetallerId(Long id);
	Long creacionObservacionConInformeId(ObservacionRequestDto observacionRequestDto);
	List<InformeTrabajadorDto> getAllInformeTrabajadorByEstado(Long id, Long empresa, Long estado);
	List<InformeInstalacionDto> getAllInformeInstalacionyEstado(Long id, Long empresa, Long estado);
}
