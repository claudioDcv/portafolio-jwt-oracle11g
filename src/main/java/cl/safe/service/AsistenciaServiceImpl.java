package cl.safe.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

	@PersistenceContext
    private EntityManager em;

	@Override
	public Long firmarAsistencia(Long asistenciaId, String firma) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("asistencia_firmar");
		query.setParameter("p_asistencia_id", asistenciaId);
		query.setParameter("p_firma_src", firma);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long inscribirEnCapacitacion(Long trabajadorId, Long capacitacionId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("asistencia_insert");
		query.setParameter("p_trabajador_fk", trabajadorId);
		query.setParameter("p_capacitacion_fk", capacitacionId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

}
