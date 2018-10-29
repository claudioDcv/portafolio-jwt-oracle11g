package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.TrabajadorEntity;
import cl.safe.repository.TrabajadorRepository;

@Service
public class TrabajadorService {
	
	@Autowired
	TrabajadorRepository trabajadorRepository;
	
	@PersistenceContext
    private EntityManager em;

	public List<TrabajadorEntity> getByEmpresaIdSP(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("TRABAJADORES_BY_EMPRESA_FK");
		query.setParameter("P_EMPRESA_FK", id);
		return query.getResultList();
	}

	public List<TrabajadorEntity> findAll() {
		return (List<TrabajadorEntity>) trabajadorRepository.findAll();
	}

	public TrabajadorEntity findOne(Long id) {
		return trabajadorRepository.findOne(id);
	}

	public TrabajadorEntity findByEmail(String email) {
		return trabajadorRepository.findByEmail(email);
	}

}
