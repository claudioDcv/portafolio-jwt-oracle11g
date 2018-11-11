package cl.safe.service;

import java.util.List;

import cl.safe.dto.InstalacionRequestDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;

public interface InstalacionService {

	public List<InstalacionEntity> getAllSP();
	public List<InstalacionEntity> getAllByEmpresaId(Long id);
	Long crearInstalacion(InstalacionRequestDto instalacionRequestDto);
}
