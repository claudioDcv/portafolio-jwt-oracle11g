package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.entity.AsistenciaUsuarioEntity;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.UserEntity;
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

	@Override
	public CapacitacionEntity findOneSP(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CAPACITACION_BY_ID");
		query.setParameter("P_ID", id);
		
		CapacitacionEntity capacitacionEntity = (CapacitacionEntity) query.getSingleResult();
		
		PolicyFactory policy = new HtmlPolicyBuilder()
			    .allowElements("a")
			    .allowUrlProtocols("https")
			    .allowElements("strong")
			    .allowElements("i")
			    .allowElements("p")
			    .allowElements("h1")
			    .allowElements("h2")
			    .allowElements("h3")
			    .allowElements("h4")
			    .allowElements("h5")
			    .allowElements("h6")
			    .allowElements("li")
			    .allowElements("small")
			    .allowElements("ul")
			    .allowElements("ol")
			    .allowElements("br")
			    .allowAttributes("href").onElements("a")
			    .requireRelNofollowOnLinks()
			    .toFactory();
			String safeHTML = policy.sanitize(capacitacionEntity.getDescripcion());
			capacitacionEntity.setDescripcion(safeHTML);
		return capacitacionEntity;
	}

	@Override
	public List<AsistenciaUsuarioEntity> findAllAsistentesByCapacitacionId(Long capacitacionId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("asistencias_get_all_by_cap_id");
		query.setParameter("P_ID", capacitacionId);
		return query.getResultList();
	}

}
