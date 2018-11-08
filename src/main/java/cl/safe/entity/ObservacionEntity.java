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
	
}
