package cl.safe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.safe.entity.VisitaMedicaEntity;

@Service
public interface VisitaMedicaService {
	List<VisitaMedicaEntity> findAll();
	public List<VisitaMedicaEntity> findAllByEmpresaMedicoConfirmacionSP(Long empresaId, Long medicoId, Integer confirmacion);
	public List<VisitaMedicaEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long superVisorId);
}
