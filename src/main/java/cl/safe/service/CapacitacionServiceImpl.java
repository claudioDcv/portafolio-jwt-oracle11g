package cl.safe.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.CapacitacionRequestDto;
import cl.safe.entity.AsistenciaTrabajadorEntity;
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
	public List<AsistenciaTrabajadorEntity> findAllAsistentesByCapacitacionId(Long capacitacionId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("asistencias_get_all_by_cap_id");
		query.setParameter("P_ID", capacitacionId);
		return query.getResultList();
	}

	@Override
	public List<CapacitacionEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long supervisorId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CAPACITACION_BY_EMP_SUP");
		query.setParameter("P_EMPRESA_FK", empresaId);
		query.setParameter("p_supervisor_fk", supervisorId);
		return query.getResultList();
	}

	@Override
	public Long crearCapacitacion(CapacitacionRequestDto capacitacionEntity) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CAPACITACIONES_INSERT");
		query.setParameter("p_examinador_fk", capacitacionEntity.getExaminador());
		query.setParameter("p_supervisor_fk", capacitacionEntity.getSupervisor());
		query.setParameter("p_nombre", capacitacionEntity.getNombre());
		query.setParameter("p_empresa_fk", capacitacionEntity.getEmpresa());
		query.setParameter("p_fecha_realizacion", capacitacionEntity.getFechaRealizacion());
		query.setParameter("p_descripcion", capacitacionEntity.getDescripcion());
		query.setParameter("p_asistentes_minimos", capacitacionEntity.getAsistentesMinimos());		
		query.execute();
		return (Long) query.getOutputParameterValue("o_ID");
	}

	@Override
	public Long cerrarCapacitacion(Long capacitacionId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("capacitacion_cerrar");
		query.setParameter("p_capacitacion_id", capacitacionId);
		query.execute();
		return (Long) query.getOutputParameterValue("p_id");
	}

}
