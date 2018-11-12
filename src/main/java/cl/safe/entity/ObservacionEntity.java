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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "OBSERVACION_BY_INFO_ID",
			procedureName = "OBSERVACION_BY_INFO_ID",
			resultClasses= { ObservacionEntity.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "OBSERVACION_INSERT",
			procedureName = "OBSERVACION_INSERT",
			resultClasses= { InformeDetalleEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_informe_detalle_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "observ_update_by_preve",
			procedureName = "observ_update_by_preve",
			resultClasses= { InformeDetalleEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_observacion_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_recomendacion", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	)
})
@Entity
@Table(name="observaciones")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ObservacionEntity {
	@Id
	@Column(name="id_observacion")
	Long id;
	String nombre;
	
	@Column(name="informe_detalle_fk")
	Long informeDetalle;
	
	@Column(name="recomendacion")
	String recomendacion;
	
}
