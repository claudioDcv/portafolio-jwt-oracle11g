package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.ObservacionEntity;
import cl.safe.entity.UserEntity;

/*
create or replace PROCEDURE INFORME_TRABAJADOR_INSERT(
	    P_TRABAJADOR_FK IN INFORMES_TRABAJADOR.TRABAJADOR_FK%TYPE,
	    P_TECNICO_FK IN INFORMES_DETALLES.TECNICO_FK%TYPE,
	    P_NOMBRE IN INFORMES_DETALLES.NOMBRE%TYPE,
	    P_SUPERVISOR_FK IN INFORMES_DETALLES.SUPERVISOR_FK%TYPE,
	    P_FECHA_REALIZACION IN INFORMES_DETALLES.FECHA_REALIZACION%TYPE,
	    O_ID OUT INFORMES_DETALLES.TECNICO_FK%TYPE)
	AS
*/

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
	
}
