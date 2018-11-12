package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.RiesgoEntity;
import cl.safe.repository.RiesgoRepository;

@Service
public class RiesgoServiceImpl implements RiesgoService {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	RiesgoRepository riesgoRepository;
	
	@Override
	public Long insertRiesgo(RiesgoRequestDto riesgoRequestDto) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("riesgo_insert");
		query.setParameter("p_nombre", riesgoRequestDto.getNombre());
		query.setParameter("p_codigo", riesgoRequestDto.getCodigo());
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public List<RiesgoEntity> findAll() {
		return (List<RiesgoEntity>) riesgoRepository.findAll();
	}

}
