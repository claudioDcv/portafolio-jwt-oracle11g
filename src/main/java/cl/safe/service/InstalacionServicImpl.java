package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;

@Service
public class InstalacionServicImpl implements InstalacionService {

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<InstalacionEntity> getAllSP() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("instalaciones_get_all");
        return query.getResultList();
	}

	@Override
	public List<InstalacionEntity> getAllByEmpresaId(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("INSTALACIONES_BY_EMPRESA_ID");
		query.setParameter("p_id", id);
		return query.getResultList();
	}

}
