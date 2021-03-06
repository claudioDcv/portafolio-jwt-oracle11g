package cl.safe.entity;

import java.io.Serializable;
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

import cl.safe.entity.EmpresaEntity.EmpresaEntityBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "instalaciones_get_all",
            procedureName = "instalaciones_get_all",
			resultClasses = InstalacionEntity.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(name = "INSTALACIONES_BY_EMPRESA_ID",
	    procedureName = "INSTALACIONES_BY_EMPRESA_ID",
		resultClasses = InstalacionEntity.class,
		parameters = {
				@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
		}
	),
	@NamedStoredProcedureQuery(name = "instalaciones_insert",
    procedureName = "instalaciones_insert",
	resultClasses = InstalacionEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(name="p_codigo", mode = ParameterMode.IN, type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_ID", type = Long.class)
		}
	)
})
@Entity
@Table(name="instalaciones")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class InstalacionEntity implements Serializable {
	private static final long serialVersionUID = -281379870411835245L;
	
	@Id
	@Column(name="instalacion_id")
	@Getter @Setter Long id;
	
	@Getter @Setter String nombre;

	@Getter @Setter String codigo;
	
	@ManyToOne
    @JoinColumn(name = "empresa_fk")
	@Getter @Setter EmpresaEntity empresaEntity;
}
