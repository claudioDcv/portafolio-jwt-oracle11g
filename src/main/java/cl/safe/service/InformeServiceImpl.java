package cl.safe.service;

import java.lang.annotation.Native;
import java.util.Date;
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
import cl.safe.dto.PaginacionObjetoResponseDto;
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
		// el id es el del usario tecnico
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

	@Override
	public List<InformeTrabajadorDto> getAllInformeTrabajadorBySupervisorId(Long supervisorId, Long idEmpresa) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informes_trab_by_super");
		query.setParameter("p_supervisor_id", supervisorId);
		query.setParameter("p_empresa_fk", idEmpresa);
		return query.getResultList();
	}

	@Override
	public List<InformeInstalacionDto> getAllInformeInstalacionyBySupervisorId(Long supervisorId, Long idEmpresa) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informes_instal_by_super");
		query.setParameter("p_supervisor_id", supervisorId);
		query.setParameter("p_empresa_fk", idEmpresa);
		return query.getResultList();
	}

	@Override
	public Long asignarPrevencionista(Long informeDetalleId, Long prevencionistaId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informe_det_asig_preve");
		query.setParameter("p_prevencionista_id", prevencionistaId);
		query.setParameter("p_detalle_id", informeDetalleId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long rechazarInformeDetalle(Long informeDetalleId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informe_detalle_rechaz");
		query.setParameter("p_detalle_id", informeDetalleId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public Long aprobarInformeDetalle(Long informeDetalleId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("informe_detalle_aprob");
		query.setParameter("p_detalle_id", informeDetalleId);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public List<InformeTrabajadorDto> getAllInformeTrabajadorByEstadoPrevencionistaId(Long preveId, Long idEmpresa,
			Long estado) {
		
		// estado se refiere a la confirmacion del prevencionista -1 0 1
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_trabajador_by_preve");
		query.setParameter("p_id_preve", preveId);
		query.setParameter("P_ID_EMPRESA", idEmpresa);
		query.setParameter("P_ESTADO", estado);
		return query.getResultList();
	}

	@Override
	public List<InformeInstalacionDto> getAllInformeInstalacionyByEstadoPrevencionistaId(Long preveId, Long idEmpresa,
			Long estado) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_instalacion_by_preve");
		query.setParameter("p_id_preve", preveId);
		query.setParameter("P_ID_EMPRESA", idEmpresa);
		query.setParameter("P_ESTADO", estado);
		return query.getResultList();
	}

	@Override
	public Long agregarRecomendacionParaObservacionPorPreve(String observacion, Long observacionId) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("observ_update_by_preve");
		query.setParameter("p_observacion_id", observacionId);
		query.setParameter("p_recomendacion", observacion);
		query.execute();
		return (Long) query.getOutputParameterValue("o_id");
	}

	@Override
	public List<InformeInstalacionDto> getAllInformeInstalacionADMINEMPRESA(Long empresa) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_instalacion_admin_empresa");
		query.setParameter("P_ID_EMPRESA", empresa);
		return query.getResultList();
	}

	@Override
	public List<InformeTrabajadorDto> getAllInformeTrabajadorADMINEMPRESA(Long empresa) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_trabajador_admin_empresa");
		query.setParameter("p_id_empresa", empresa);
		return query.getResultList();
	}
	
	@Override
	public PaginacionObjetoResponseDto<InformeTrabajadorDto> getAllInformeTrabajadorADMINEMPRESA_PAG(
			Long empresa,
			Long pageNumber,
			Long pageSize,
			Date fromDate,
			Date toDate
			) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_trab_admin_empresa_dat");

		System.out.println("PAGINACION DATA");
		System.out.println("empresa:" + empresa +
				",pageNumber:" + pageNumber +
				",pageSize:" + pageSize +
				",from_date:" + fromDate +
				",to_date:" + toDate);
		query.setParameter("p_id_empresa", empresa);
		query.setParameter("p_page_number", pageNumber);
		query.setParameter("p_page_size", pageSize);
		
		query.setParameter("p_from_date", fromDate);
		query.setParameter("p_to_date", toDate);
		
		
		
		
		PaginacionObjetoResponseDto<InformeTrabajadorDto> l = new PaginacionObjetoResponseDto<>();
		l.initialized((Long) query.getOutputParameterValue("o_count"), pageSize, pageNumber);
		l.setList(query.getResultList());
		
		/* LOGICA PAGINACION */	
		return l;
	}

	@Override
	public PaginacionObjetoResponseDto<InformeInstalacionDto> getAllInformeInstalacionADMINEMPRESA_PAG(Long empresa, Long pageNumber,
			Long pageSize, Date fromDate, Date toDate) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("info_inst_admin_empresa_dat");

		System.out.println("PAGINACION DATA");
		System.out.println("empresa:" + empresa +
				",pageNumber:" + pageNumber +
				",pageSize:" + pageSize +
				",from_date:" + fromDate +
				",to_date:" + toDate);
		query.setParameter("p_id_empresa", empresa);
		query.setParameter("p_page_number", pageNumber);
		query.setParameter("p_page_size", pageSize);
		
		query.setParameter("p_from_date", fromDate);
		query.setParameter("p_to_date", toDate);
		
		
		
		
		PaginacionObjetoResponseDto<InformeInstalacionDto> l = new PaginacionObjetoResponseDto<>();
		l.initialized((Long) query.getOutputParameterValue("o_count"), pageSize, pageNumber);
		l.setList(query.getResultList());
		
		/* LOGICA PAGINACION */	
		return l;
	}
	
}
