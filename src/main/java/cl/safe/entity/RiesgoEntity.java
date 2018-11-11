package cl.safe.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeTrabajadorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "riesgo_insert",
			procedureName = "riesgo_insert",
			resultClasses= { RiesgoEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_codigo", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_id", type = Long.class)
			}
	)
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="riesgos")
public class RiesgoEntity implements Serializable {
	private static final long serialVersionUID = -6024902964957106842L;
	String nombre;
	String codigo;
	
	@Id
	@Column(name="riesgo_id")
	Long id;
}