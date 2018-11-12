package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.dto.ConsultaMedicaRequestDto;
import cl.safe.dto.VisitaMedicaRequestDto;
import cl.safe.entity.ConsultaMedicaEntity;
import cl.safe.entity.VisitaMedicaEntity;
import cl.safe.repository.VisitaMedicaRepository;

@Service
public class VisitaMedicaServiceImpl implements VisitaMedicaService{
	
	@Autowired
	VisitaMedicaRepository visitaMedicaRepository;
	
	@PersistenceContext
    private EntityManager em;

	@Override
	public List<VisitaMedicaEntity> findAll() {
		return (List<VisitaMedicaEntity>) visitaMedicaRepository.findAll();
	}

	public List<VisitaMedicaEntity> findAllByEmpresaMedicoConfirmacionSP(Long empresaId, Long medicoId, Integer confirmacion) {
		// tres estados posibles <-1 0 1>
		if (confirmacion == 0 || confirmacion == -1 || confirmacion == 1) {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("VISITAS_MED_BY_EMP_MED_CONF");
			query.setParameter("P_EMPRESA_FK", empresaId);
			query.setParameter("P_MEDICO_FK", medicoId);
			query.setParameter("P_CONFIRMACION_MEDICO", confirmacion);
			return query.getResultList();

		}
		return null;
	}

	@Override
	public List<VisitaMedicaEntity> findAllByEmpresaSupervisorSP(Long empresaId, Long superVisorId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("VISITAS_MED_BY_EMP_SUP_CONF");
		query.setParameter("p_empresa_fk", empresaId);
		query.setParameter("p_supervisor_fk", superVisorId);
		return query.getResultList();
	}

	@Override
	public Long crearVisitaMedica(VisitaMedicaRequestDto visitaMedicaRequestDto) {
		// visita_medica_insert
		/*
			p_medico_fk in number,
		    p_supervisor_fk in number,
		    p_empresa_fk in number,
		    p_fecha_realizacion in visitas_medicas.fecha_realizacion%type,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("visita_medica_insert");
		query.setParameter("p_medico_fk", visitaMedicaRequestDto.getMedicoId());
		query.setParameter("p_supervisor_fk", visitaMedicaRequestDto.getSupervisorId());
		query.setParameter("p_empresa_fk", visitaMedicaRequestDto.getEmpresaId());
		query.setParameter("p_fecha_realizacion", visitaMedicaRequestDto.getFechaRealizacion());
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long crearConsultaMedica(ConsultaMedicaRequestDto consultaMedicaRequestDto) {
		/*
		create or replace procedure consulta_medica_insert(
	    p_trabajador_fk in number,
	    p_visita_medica_fk in number,
	    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("consulta_medica_insert");
		query.setParameter("p_trabajador_fk", consultaMedicaRequestDto.getTrabajadorId());
		query.setParameter("p_visita_medica_fk", consultaMedicaRequestDto.getVisitaMedicaId());
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long cerrarConsultaMedica(Long consultaMedicaId) {
		/*
			create or replace procedure consulta_medica_cerrar(
		    p_consulta_medica_id in number,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("consulta_medica_cerrar");
		query.setParameter("p_consulta_medica_id", consultaMedicaId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long asignarExamenConsultaMedica(Long consultaMedicaid, Long examenId) {
		/*
			create or replace procedure examen_asignar_consulta_medica(
		    p_consulta_fk in number,
		    p_examen_fk in number,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("examen_asignar_consulta_medica");
		query.setParameter("p_consulta_fk", consultaMedicaid);
		query.setParameter("p_examen_fk", examenId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long cerrarVisitaMedica(Long visitaMedicaId) {
		/*
			create or replace procedure visita_medica_cerrar(
		    p_visita_medica_id in number,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("visita_medica_cerrar");
		query.setParameter("p_visita_medica_id", visitaMedicaId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long aceptarVisitaMedica(Long visitaMedicaId) {
		/*
		 	create or replace procedure visita_medica_aceptar(
		    p_visita_medica_id in number,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("visita_medica_aceptar");
		query.setParameter("p_visita_medica_id", visitaMedicaId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long rechazarVisitaMedica(Long visitaMedicaId) {
		/*
			create or replace procedure visita_medica_rechazar(
		    p_visita_medica_id in number,
		    o_id out number
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("visita_medica_rechazar");
		query.setParameter("p_visita_medica_id", visitaMedicaId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public VisitaMedicaEntity getVisitaMedicaById(Long empresaId, Long visitaMedicaId) {
		/*
	    	procedureName = "VISITA_MEDICA_BY_ID_EMPRESA_FK",
			@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_visita_id", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("VISITA_MEDICA_BY_ID_EMPRESA_FK");
		query.setParameter("p_empresa_fk", empresaId);
		query.setParameter("p_visita_id", visitaMedicaId);
		return (VisitaMedicaEntity) query.getSingleResult();
	}

	@Override
	public List<ConsultaMedicaEntity> getAllConsultasMedicasByVisitaMedica(Long visitaMedicaId) {
		/*
		 * procedureName = "CONSULTA_BY_VISITA_MED_ID",
			resultClasses = ConsultaMedicaEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_visita_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
				}

		 */
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("CONSULTA_BY_VISITA_MED_ID");
		query.setParameter("p_visita_medica_id", visitaMedicaId);
		return query.getResultList();
	}	
}
