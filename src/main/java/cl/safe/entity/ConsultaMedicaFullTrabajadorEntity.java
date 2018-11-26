package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "consulta_by_emp_vis_run",
            procedureName = "consulta_by_emp_vis_run",
			resultClasses = ConsultaMedicaFullTrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_empresa_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_consulta_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_cursor", type = void.class)
			}
	),
})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ConsultaMedicaFullTrabajadorEntity {
	
	@Column(name="ID")
	@Id Long id;
	
	@Column(name="TRABAJADOR_ID")
	Long trabajadorId;
	
	@Column(name="EMPRESA_FK")
	Long empresaFk;
	
	@Column(name="NOMBRE")
	String nombre;
	
	@Column(name="APELLIDO_MATERNO")
	String apellidoMaterno;
	
	@Column(name="APELLIDO_PATERNO")
	String apellidoPaterno;
	
	@Column(name="RUN")
	String run;
	
	@Column(name="EMAIL")
	String email;
	
	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
	
	@Column(name="VISITA_MEDICA_ID")
	Long visitaMedicaId;
	
	@Column(name="MEDICO_NOMBRE")
	String medicoNombre;
	
	@Column(name="MEDICO_EMAIL")
	String medicoEmail;
}
