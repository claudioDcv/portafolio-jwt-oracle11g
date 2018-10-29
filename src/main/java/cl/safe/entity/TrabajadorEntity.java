package cl.safe.entity;

import java.io.Serializable;
import java.util.List;

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
	@NamedStoredProcedureQuery(name = "TRABAJADORES_BY_EMPRESA_FK",
            procedureName = "TRABAJADORES_BY_EMPRESA_FK",
			resultClasses = TrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="P_EMPRESA_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	)
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trabajadores")
public class TrabajadorEntity implements Serializable {
	
	@Id
	@Column(name="trabajador_id")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "empresa_fk")
	@Getter @Setter EmpresaEntity empresaEntity;

	String nombre;
	
	@Column(name="apellido_paterno")
	String apellidoPaterno;
	
	@Column(name="apellido_materno")
	String apellidoMaterno;
	String email;
	String run;
}
