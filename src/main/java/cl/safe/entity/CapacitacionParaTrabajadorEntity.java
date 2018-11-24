package cl.safe.entity;

import java.io.Serializable;
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
	@NamedStoredProcedureQuery(name = "get_all_mis_certificados",
            procedureName = "get_all_mis_certificados",
			resultClasses = CapacitacionParaTrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_empresa_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_cursor", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(name = "get_by_id_mi_certificado",
	    procedureName = "get_by_id_mi_certificado",
		resultClasses = CapacitacionParaTrabajadorEntity.class,
		parameters = {
				@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
				@StoredProcedureParameter(name="p_empresa_id", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(name="p_asistencia_id", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_cursor", type = void.class)
		}
	)
})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CapacitacionParaTrabajadorEntity implements Serializable {
	private static final long serialVersionUID = 7513903468024590889L;

	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
	
	@Column(name="CAPACITACION_NOMBRE")
	String capacitacionNombre;
	
	@Column(name="CAPACITACION_ID")
	Long capacitacionId;
	
	@Id
	@Column(name="ID")
	Long id;
	
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
	
	@Column(name="NOMBRE_EMPRESA")
	String nombreEmpresa;
	
	@Column(name="EMPRESA_ID")
	Long empresaId;
	
	@Column(name="ASISTENCIA_ID")
	Long asistenciaId;
}
