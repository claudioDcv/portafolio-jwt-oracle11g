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

import cl.safe.dto.InformeInstalacionDto;
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
	@NamedStoredProcedureQuery(
			name = "INFORME_INSTALACION_INSERT",
			procedureName = "INFORME_INSTALACION_INSERT",
			resultClasses= { InformeDetalleEntity.class},
			parameters = {
					@StoredProcedureParameter(name="P_INSTALACION_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_TECNICO_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_NOMBRE", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="P_SUPERVISOR_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="P_FECHA_REALIZACION", mode = ParameterMode.IN, type = Date.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "O_ID", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "informe_instalacion_by_id",
			procedureName = "informe_instalacion_by_id",
			resultClasses= { InformeInstalacionDto.class},
			parameters = {
					@StoredProcedureParameter(name="P_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
})
@Table(name="INFORMES_DETALLES")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformeDetalleEntity {
	
	@Id
	@Column(name="INFORME_DETALLE_ID")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "prevencionista_fk")
	UserEntity prevencionista;
	
	@ManyToOne
    @JoinColumn(name = "supervisor_fk")
	UserEntity supervisor;
	
	@ManyToOne
    @JoinColumn(name = "tecnico_fk")
	UserEntity tecnico;
	
	@Column(name = "nombre")
	String nombre;
	
	@Column(name="fecha_realizacion")
	Date fechaRealizacion;
	
	@Column(name="fecha_confirmacion")
	Date fechaConfirmacion;

	@Column(name="confirmacion_prevencionista")
	Boolean confirmacionPrevencionista; 
}
