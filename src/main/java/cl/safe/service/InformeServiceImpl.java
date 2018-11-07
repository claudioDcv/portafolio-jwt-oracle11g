package cl.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import cl.safe.dto.InformeTrabajadorDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;

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
	public Long creacionInformeTrabajador(InformeTrabajadorDto informeTrabajadorDto) {
		System.out.println("1 __________________________________________________");
		System.out.println(informeTrabajadorDto);
		System.out.println(informeTrabajadorDto.getTrabajador());
		System.out.println(informeTrabajadorDto.getTecnico());
		System.out.println(informeTrabajadorDto.getNombre());
		System.out.println(informeTrabajadorDto.getSupervisor());
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("INFORME_TRABAJADOR_INSERT");
		query.setParameter("P_TRABAJADOR_FK", informeTrabajadorDto.getTrabajador());
		query.setParameter("P_TECNICO_FK", informeTrabajadorDto.getTecnico());
		query.setParameter("P_NOMBRE", informeTrabajadorDto.getNombre());
		query.setParameter("P_SUPERVISOR_FK", informeTrabajadorDto.getSupervisor());
		query.setParameter("P_FECHA_REALIZACION", informeTrabajadorDto.getFechaRealizacion());
		System.out.println("2 __________________________________________________");;
		query.execute();
		System.out.println("3 __________________________________________________");
		return (Long) query.getOutputParameterValue("O_ID");
	}

}
