package cl.safe.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@NamedStoredProcedureQuery(name = "trabajador_insert",
            procedureName = "trabajador_insert",
			resultClasses = TrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="p_apellido_materno", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_apellido_paterno", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_email", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_nombre", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_ID", type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(name = "TRABAJADORES_BY_EMPRESA_FK",
            procedureName = "TRABAJADORES_BY_EMPRESA_FK",
			resultClasses = TrabajadorEntity.class,
			parameters = {
					@StoredProcedureParameter(name="P_EMPRESA_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "trab_riesgo_del_by_us_id",
			procedureName = "trab_riesgo_del_by_us_id",
			resultClasses= { TrabajadorEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="O_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "trabajador_riesgo_insert",
			procedureName = "trabajador_riesgo_insert",
			resultClasses= { TrabajadorEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_riesgo_FK", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="p_trabajador_fk", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_id", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(name = "trabajadores_riesgo_all",
    procedureName = "trabajadores_riesgo_all",
	resultClasses = TrabajadorEntity.class,
	parameters = {
			@StoredProcedureParameter(name="p_empresa_fk", mode = ParameterMode.IN, type = Long.class),
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "O_CURSOR", type = void.class)
		}
	),
	@NamedStoredProcedureQuery(
			name = "trabajador_by_run_empresa_id",
			procedureName = "trabajador_by_run_empresa_id",
			resultClasses= { TrabajadorEntity.class},
			parameters = {
					@StoredProcedureParameter(name="p_run", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_empresa_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="numero_usuarios", mode = ParameterMode.OUT, type = Integer.class)
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
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
		name = "trabajadores_riesgos",
		joinColumns = @JoinColumn(name="trabajadores_fk", referencedColumnName="trabajador_id"),
		inverseJoinColumns = @JoinColumn(
			name = "riesgos_fk",
			referencedColumnName = "riesgo_id"
		)
	)
	@Getter @Setter List<RiesgoEntity> riesgos;
}
