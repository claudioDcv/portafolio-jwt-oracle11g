package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;

@Service
public class InstalacionServiceImpl implements InstalacionService {

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<InstalacionEntity> getAllSP() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("instalaciones_get_all");
        return query.getResultList();
	}

}
