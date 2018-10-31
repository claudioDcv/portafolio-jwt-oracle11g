package cl.safe.service;

import java.util.List;

import cl.safe.entity.AsistenciaUsuarioEntity;
import cl.safe.entity.CapacitacionEntity;
public interface CapacitacionService {
	public CapacitacionEntity findOneSP(Long id); 
	public List<CapacitacionEntity> findAllByEmpresaExaminadorSP(Long empresaId, Long examinadorId);

	public List<AsistenciaUsuarioEntity> findAllAsistentesByCapacitacionId(Long capacitacionId);
}
