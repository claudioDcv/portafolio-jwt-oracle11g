package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.entity.CapacitacionEntity;
import cl.safe.repository.EmpresaRepository;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<CapacitacionEntity> findAllByEmpresaExaminadorSP(Long empresaId, Long examinadorId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CAPACITACION_BY_EMP_EXA");
		query.setParameter("P_EMPRESA_FK", empresaId);
		query.setParameter("p_examinador_fk", examinadorId);
		return query.getResultList();
	}

}
