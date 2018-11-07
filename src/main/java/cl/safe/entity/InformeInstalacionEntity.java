package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "INFORME_TRABAJADOR_INSERT",
			procedureName = "INFORME_TRABAJADOR_INSERT",
			resultClasses= { InformeDetalleEntity.class},
			parameters = {
					@StoredProcedureParameter(name="P_TRABAJADOR_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_TECNICO_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_NOMBRE", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="P_SUPERVISOR_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_FECHA_REALIZACION", mode = ParameterMode.IN, type = Date.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "O_ID", type = Long.class)
			}
	),
})
@Table(name="INFORMES_INSTALACION")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformeInstalacionEntity {
	
	@Id
	@Column(name="INFORME_INSTALACION_ID")
	Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informe_detalle_fk")
	InformeDetalleEntity informeDetalle;
	
	@ManyToOne
    @JoinColumn(name = "instalacion_fk")
	InstalacionEntity instalacion;
}
