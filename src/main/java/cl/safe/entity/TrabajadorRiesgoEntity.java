package cl.safe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="trabajadores_riesgos")
public class TrabajadorRiesgoEntity implements Serializable {

	private static final long serialVersionUID = 8701186594866315620L;

	@Id
	@Column(name="trabajador_riesgo_id")
	Long id;
	
	
	@Column(name="trabajadores_fk")
	TrabajadorEntity trabajadoresFk;
	
	@Column(name="riesgosEntity_fk")
	RiesgoEntity riesgosEntityFk;
}
