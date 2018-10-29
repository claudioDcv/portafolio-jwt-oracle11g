package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.entity.EmpresaEntity;
import cl.safe.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService{

	@Autowired
	EmpresaRepository empresaRepository;

	@PersistenceContext
    private EntityManager em;

	@Override
	public List<EmpresaEntity> getAllSP() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("empresas_get_all");
        return query.getResultList();
	}
	
	public EmpresaEntity findOne(Long id) {
		return empresaRepository.findOne(id);
	}
	
}
