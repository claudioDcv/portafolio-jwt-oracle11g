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
	UserEntity medico;
	
	@ManyToOne
    @JoinColumn(name = "SUPERVISOR_FK")
	UserEntity supervisor;
	
	@ManyToOne
    @JoinColumn(name = "EMPRESA_FK")
	EmpresaEntity empresa;
	
	@Column(name="FECHA_REALIZACION")
	Date fechaRealizacion;
}
