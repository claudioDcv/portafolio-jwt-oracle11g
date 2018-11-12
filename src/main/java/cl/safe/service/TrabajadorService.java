package cl.safe.service;

import java.util.List;

import cl.safe.dto.TrabajadorRequestDto;
import cl.safe.entity.TrabajadorEntity;

public interface TrabajadorService {

	List<TrabajadorEntity> getByEmpresaIdSP(Long id);

	List<TrabajadorEntity> findAll();

	TrabajadorEntity findOne(Long id);

	TrabajadorEntity findByEmail(String email);

	Long crearTrabajadorSP(TrabajadorRequestDto trabajadorRequestDto);
	
	Long asignarRiesgosConTrabajadorId(List<Long> riesgosIds, Long trabajadorId);
	
	List<TrabajadorEntity> findAllTrabajadoresRiesgoByEmpresaId(Long empresaId);
}
