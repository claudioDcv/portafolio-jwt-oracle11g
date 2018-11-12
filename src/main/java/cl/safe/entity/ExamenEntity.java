package cl.safe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import cl.safe.dto.InformeTrabajadorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "examen_insert",
			procedureName = "examen_insert",
			resultClasses= { ExamenEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_codigo", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "EXAMENES_BY_CONSULTA_MED",
			procedureName = "EXAMENES_BY_CONSULTA_MED",
			resultClasses= { ExamenEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_consulta_medica_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	)
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="examenes")
public class ExamenEntity implements Serializable {
	private static final long serialVersionUID = -4794907874257851534L;
	String nombre;
	String codigo;
	
	@Id
	@Column(name="examen_id")
	Long id;
}
