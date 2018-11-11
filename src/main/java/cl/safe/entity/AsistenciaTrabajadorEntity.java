package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@NamedStoredProcedureQuery(
			name = "asistencias_get_all_by_cap_id",
			procedureName = "asistencias_get_all_by_cap_id",
			resultClasses= { AsistenciaTrabajadorEntity.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	)
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaTrabajadorEntity {
	
	@Id
	@Column(name="asistencia_id")
	Long asistenciaId;

	@Column(name="trabajador_id")
	Long trabajadorId;
	
	@Column(name="capacitacion_fk")
	Long capacitacionId;
	
	String nombre;
	
	@Column(name="apellido_paterno")
	String apellidoPaterno;
	
	@Column(name="apellido_materno")
	String apellidoMaterno;

	String email;
	String run;
	String firma;
}
