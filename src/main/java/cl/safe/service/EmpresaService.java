package cl.safe.service;

import java.util.List;

import cl.safe.entity.EmpresaEntity;

public interface EmpresaService {

	public List<EmpresaEntity> getAllSP();
	public EmpresaEntity findOne(Long id);
}
