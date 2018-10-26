package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.entity.EmpresaEntity;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<EmpresaEntity> getAllSP() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("empresas_get_all");
        return query.getResultList();
	}

}
