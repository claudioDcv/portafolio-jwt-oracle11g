package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.ExamenRequestDto;
import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.ExamenEntity;
import cl.safe.repository.ExamenRepository;

@Service
public class ExamenServiceImpl implements ExamenService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	ExamenRepository examenrepository;
	
	@Override
	public Long insertExamen(ExamenRequestDto examenRequestDto) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("examen_insert");
		query.setParameter("p_nombre", examenRequestDto.getNombre());
		query.setParameter("p_codigo", examenRequestDto.getCodigo());
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public List<ExamenEntity> findAll() {
		return (List<ExamenEntity>) examenrepository.findAll();
	}
}
