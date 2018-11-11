package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.entity.VisitaMedicaEntity;
import cl.safe.repository.VisitaMedicaRepository;

@Service
public class VisitaMedicaServiceImpl implements VisitaMedicaService{
	
	@Autowired
	VisitaMedicaRepository visitaMedicaRepository;
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<VisitaMedicaEntity> findAll() {
		return (List<VisitaMedicaEntity>) visitaMedicaRepository.findAll();
	}

	public List<VisitaMedicaEntity> findAllByEmpresaMedicoConfirmacionSP(Long empresaId, Long medicoId, Integer confirmacion) {
		// tres estados posibles <-1 0 1>
		/*
		 * P_EMPRESA_FK NUMBER;
		  P_MEDICO_FK NUMBER;
		  P_CONFIRMACION_MEDICO NUMBER;
		  O_CURSOR SYS_REFCURSOR;
		 */
		if (confirmacion == 0 || confirmacion == -1 || confirmacion == 1) {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("VISITAS_MED_BY_EMP_MED_CONF");
			query.setParameter("P_EMPRESA_FK", empresaId);
			query.setParameter("P_MEDICO_FK", medicoId);
			query.setParameter("P_CONFIRMACION_MEDICO", confirmacion);
			return query.getResultList();

		}
		return null;
	}

	@Override
	public List<VisitaMedicaEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long superVisorId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("VISITAS_MED_BY_EMP_SUP_CONF");
		query.setParameter("p_empresa_fk", empresaId);
		query.setParameter("p_supervisor_fk", superVisorId);
		return query.getResultList();
	}
	
	
}
