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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "capa_por_hacer_trab",
	    procedureName = "capa_por_hacer_trab",
		resultClasses = CapacitacionPorHacerTrabajadorEntity.class,
		parameters = {
				@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
				@StoredProcedureParameter(name="p_empresa_id", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_cursor", type = void.class)
		}
	)
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacitacionPorHacerTrabajadorEntity {


	@Column(name="CAPACITACION_NOMBRE")
	String capacitacionNombre;
	
	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
	
	@Column(name="TRABAJADOR_ID")
	Long trabajadorId;
	
	@Column(name="EMPRESA_ID")
	Long empresaId;
	
	@Column(name="DESCRIPCION")
	String descripcion;
	
	@Id
	@Column(name="CAPACITACION_ID")
	String capacitacionId;
}
