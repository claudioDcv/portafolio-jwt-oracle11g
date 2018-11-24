package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.dto.ConsultaMedicaTrabajadorResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;
import cl.safe.entity.ConsultaMedicaTrabajadorEntity;

@Service
public class CertificadoServiceImpl implements CertificadoService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<CapacitacionParaTrabajadorResponseDto> getAllCetificados(String run, Long empresaId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("get_all_mis_certificados");
		query.setParameter("p_run", run);
		query.setParameter("p_empresa_id", empresaId);
		return query.getResultList();
	}

	@Override
	public CapacitacionParaTrabajadorEntity getCetificado(String run, Long empresaId, Long asistenciaId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("get_by_id_mi_certificado");
		query.setParameter("p_run", run);
		query.setParameter("p_empresa_id", empresaId);
		query.setParameter("p_asistencia_id", asistenciaId);
		return (CapacitacionParaTrabajadorEntity) query.getSingleResult();
	}

	@Override
	public List<ConsultaMedicaTrabajadorResponseDto> getAllConsultas(String run, Long empresaId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("consulta_by_trabajador_run");
		query.setParameter("p_run", run);
		query.setParameter("p_empresa_id", empresaId);
		return query.getResultList();
	}

}