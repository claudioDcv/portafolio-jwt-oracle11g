package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * P_EMPRESA_FK NUMBER;
  P_MEDICO_FK NUMBER;
  P_CONFIRMACION_MEDICO NUMBER;
  O_CURSOR SYS_REFCURSOR;
 */
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "VISITAS_MED_BY_EMP_MED_CONF",
            procedureName = "VISITAS_MED_BY_EMP_MED_CONF",
			resultClasses = VisitaMedicaEntity.class,
			parameters = {
					@StoredProcedureParameter(name="P_EMPRESA_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_MEDICO_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_CONFIRMACION_MEDICO", mode = ParameterMode.IN, type = Integer.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(name = "VISITAS_MED_BY_EMP_SUP_CONF",
    procedureName = "VISITAS_MED_BY_EMP_SUP_CONF",
	resultClasses = VisitaMedicaEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_supervisor_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
		}
	)
})
@Table(name="VISITAS_MEDICAS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitaMedicaEntity {
	
	@Id
	@Column(name="VISITA_MEDICA_ID")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "MEDICO_FK")
	UserEntity medico;
	
	@ManyToOne
    @JoinColumn(name = "SUPERVISOR_FK")
	UserEntity supervisor;
	
	@Column(name="CONFIRMACION_MEDICO")
	Integer confirmacionMedico;
	
	@ManyToOne
    @JoinColumn(name = "EMPRESA_FK")
	EmpresaEntity empresa;
	
	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
}
