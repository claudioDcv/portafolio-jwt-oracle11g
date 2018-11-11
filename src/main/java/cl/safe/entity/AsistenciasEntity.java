package cl.safe.entity;

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
	@NamedStoredProcedureQuery(name = "asistencia_insert",
            procedureName = "asistencia_insert",
			resultClasses = AsistenciasEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_trabajador_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_capacitacion_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(name = "asistencia_firmar",
    procedureName = "asistencia_firmar",
	resultClasses = AsistenciasEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_asistencia_id", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_firma_src", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
		}
	)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="asistencias")
public class AsistenciasEntity {
	
	@Id
	@Column(name="asistencia_id")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "capacitacion_fk")
	CapacitacionEntity capacitacion;
	
	@ManyToOne
    @JoinColumn(name = "trabajador_fk")
	TrabajadorEntity trabajador;
	
	String firma;
}
