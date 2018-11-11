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
	),
	//////// SP FINALES /////
	@NamedStoredProcedureQuery(
			name = "visita_medica_insert",
			procedureName = "visita_medica_insert",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_medico_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_supervisor_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_fecha_realizacion", mode = ParameterMode.IN, type = Date.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "consulta_medica_insert",
			procedureName = "consulta_medica_insert",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_trabajador_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_visita_medica_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "consulta_medica_cerrar",
			procedureName = "consulta_medica_cerrar",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_consulta_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "examen_asignar_consulta_medica",
			procedureName = "examen_asignar_consulta_medica",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_consulta_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_examen_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "visita_medica_cerrar",
			procedureName = "visita_medica_cerrar",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_visita_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "visita_medica_aceptar",
			procedureName = "visita_medica_aceptar",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_visita_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "visita_medica_rechazar",
			procedureName = "visita_medica_rechazar",
			resultClasses= { VisitaMedicaEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_visita_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
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
