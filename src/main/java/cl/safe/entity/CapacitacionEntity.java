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
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;

import cl.safe.dto.InformeInstalacionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "CAPACITACION_BY_EMP_EXA",
            procedureName = "CAPACITACION_BY_EMP_EXA",
			resultClasses = CapacitacionEntity.class,
			parameters = {
					@StoredProcedureParameter(name="P_EMPRESA_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_examinador_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "CAPACITACION_BY_ID",
			procedureName = "CAPACITACION_BY_ID",
			resultClasses= { CapacitacionEntity.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(name = "CAPACITACION_BY_EMP_SUP",
	    procedureName = "CAPACITACION_BY_EMP_SUP",
		resultClasses = CapacitacionEntity.class,
		parameters = {
				@StoredProcedureParameter(name="P_EMPRESA_FK", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(name="p_supervisor_fk", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
		}
	),
	@NamedStoredProcedureQuery(name = "CAPACITACIONES_INSERT",
    procedureName = "CAPACITACIONES_INSERT",
	resultClasses = CapacitacionEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_examinador_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_supervisor_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_fecha_realizacion", mode = ParameterMode.IN, type = Date.class),
			@StoredProcedureParameter(name="p_descripcion", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(name="p_asistentes_minimos", mode = ParameterMode.IN, type = Integer.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_ID", type = Long.class)
		}
	),
	@NamedStoredProcedureQuery(name = "capacitacion_cerrar",
    procedureName = "capacitacion_cerrar",
	resultClasses = CapacitacionEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_capacitacion_id", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "p_id", type = Long.class)
		}
	),
	@NamedStoredProcedureQuery(
			name = "capa_admin_empresa_dat",
			procedureName = "capa_admin_empresa_dat",
			resultClasses= { CapacitacionEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_id_empresa", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_page_number", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_page_size", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_from_date", mode = ParameterMode.IN, type = Date.class),
					@StoredProcedureParameter(name="p_to_date", mode = ParameterMode.IN, type = Date.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_count", type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_cursor", type = void.class)
			}
	)
})
@Table(name="CAPACITACIONES")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacitacionEntity {

	@Id
	@Column(name="CAPACITACION_ID")
	Long id;
	
	@Column(name="ASISTENTES_MINIMOS")
	Integer asistentesMinimos;

	@Column(name="NOMBRE")
	String nombre;
	
	@Column(name="DESCRIPCION")
	String descripcion;
	
	@ManyToOne
    @JoinColumn(name = "EXAMINADOR_FK")
	UserEntity examinador;
	
	@ManyToOne
    @JoinColumn(name = "SUPERVISOR_FK")
	UserEntity supervisor;
	
	@ManyToOne
    @JoinColumn(name = "EMPRESA_FK")
	EmpresaEntity empresa;
	
	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
	
	@Column(name="ESTADO")
	Integer estado;
}
