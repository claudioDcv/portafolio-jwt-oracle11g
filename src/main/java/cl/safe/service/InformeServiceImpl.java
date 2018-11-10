package cl.safe.service;

import java.lang.annotation.Native;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.InformeTrabajadorDto;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.dto.ObservacionRequestDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InformeDetalleEntity;
import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.ObservacionEntity;
import cl.safe.entity.UserEntity;

@Service
public class InformeServiceImpl implements InformeService {

	@PersistenceContext
    private EntityManager em;

	@Override
	public Long creacionInformeTrabajador(InformeTrabajadorRequestDto informeTrabajadorDto) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("INFORME_TRABAJADOR_INSERT");
		query.setParameter("P_TRABAJADOR_FK", informeTrabajadorDto.getTrabajador());
		query.setParameter("P_TECNICO_FK", informeTrabajadorDto.getTecnico());
		query.setParameter("P_NOMBRE", informeTrabajadorDto.getNombre());
		query.setParameter("P_SUPERVISOR_FK", informeTrabajadorDto.getSupervisor());
		query.setParameter("P_FECHA_REALIZACION", informeTrabajadorDto.getFechaRealizacion());
		query.execute();
		return (Long) query.getOutputParameterValue("O_ID");
	}

	@Override
	public Long creacionInformeInstalacion(InformeInstalacionDtoRequest informeInstalacionDto) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("INFORME_INSTALACION_INSERT");
		query.setParameter("P_INSTALACION_FK", informeInstalacionDto.getInstalacion());
		query.setParameter("P_TECNICO_FK", informeInstalacionDto.getTecnico());
		query.setParameter("P_NOMBRE", informeInstalacionDto.getNombre());
		query.setParameter("P_SUPERVISOR_FK", informeInstalacionDto.getSupervisor());
		query.setParameter("P_FECHA_REALIZACION", informeInstalacionDto.getFechaRealizacion());
		query.execute();
		return (Long) query.getOutputParameterValue("O_ID");
	}

	@Override
	public InformeInstalacionDto getInformeInstalacionById(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informe_instalacion_by_id");
		query.setParameter("P_ID", id);
		return (InformeInstalacionDto) query.getSingleResult();
	}
	
	@Override
	public List<ObservacionEntity> getObservacionByInformeDetallerId(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("OBSERVACION_BY_INFO_ID");
		query.setParameter("P_ID", id);
		return query.getResultList();
	}

	@Override
	public Long creacionObservacionConInformeId(ObservacionRequestDto observacionRequestDto) {
		System.out.println(observacionRequestDto);

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("OBSERVACION_INSERT");
		query.setParameter("p_nombre", observacionRequestDto.getNombre());
		query.setParameter("p_informe_detalle_fk", observacionRequestDto.getInformeId());
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}
	
	@Override
	public List<InformeTrabajadorDto> getAllInformeTrabajadorByEstado(Long id, Long empresa, Long estado) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_trabajador_by_estado");
		query.setParameter("P_ID", id);
		query.setParameter("P_ID_EMPRESA", empresa);
		query.setParameter("P_ESTADO", estado);
		return query.getResultList();
	}

	@Override
	public List<InformeInstalacionDto> getAllInformeInstalacionyEstado(Long id, Long empresa, Long estado) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_instalacion_by_estado");
		query.setParameter("P_ID", id);
		query.setParameter("P_ID_EMPRESA", empresa);
		query.setParameter("P_ESTADO", estado);
		return query.getResultList();
	}
	
	@Override
	public Boolean solicitarRevisionInformeDetalle(Long informeDetalleId, Long tecnicoId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("solicitud_revision_ifo_deta");
		query.setParameter("P_TECNICO_ID", tecnicoId);
		query.setParameter("P_ID", informeDetalleId);
		query.execute();
		Integer result = (Integer) query.getOutputParameterValue("O_RESULT");
		return result != 0;
	}

	@Override
	public InformeTrabajadorDto getInformeTrabajadorById(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informe_trabajador_by_id");
		query.setParameter("P_ID", id);
		return (InformeTrabajadorDto) query.getSingleResult();
	}
}
