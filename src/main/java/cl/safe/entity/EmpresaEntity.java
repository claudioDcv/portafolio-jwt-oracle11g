package cl.safe.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import cl.safe.entity.UserEntity.UserEntityBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "empresas_get_all",
            procedureName = "empresas_get_all",
			resultClasses = EmpresaEntity.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	)
})
@Entity
@Table(name="empresas")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmpresaEntity implements Serializable{

	private static final long serialVersionUID = -1627274060992950061L;

	@Id
	@Column(name="empresa_id")
	@Getter @Setter Long id;
	
	@Getter @Setter String nombre;
	@Getter @Setter String direccion;
	@Getter @Setter String telefono;
	@Getter @Setter String email;
}
