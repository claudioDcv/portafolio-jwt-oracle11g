package cl.safe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="consultas_medicas")
public class ConsultaMedicaEntity {
	
	@Column(name="estado_consulta")
	Integer estadoConsulta;
	
	@Column(name="visitas_medicas_fk")
	Long visitaMedicaId;

	@ManyToOne
    @JoinColumn(name = "trabajador_fk")
	TrabajadorEntity trabajador;
	
	@Id
	@Column(name="consulta_medica_id")
	Long id;
}
