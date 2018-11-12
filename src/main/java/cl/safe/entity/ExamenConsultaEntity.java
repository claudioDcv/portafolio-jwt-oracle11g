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
@Table(name="examenes_consultas")
public class ExamenConsultaEntity {
	@Id
	@Column(name="examen_consulta_id")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "examenes_fk")
	ExamenEntity examen;
	
	@Column(name="consultas_fk")
	Long consulta;
}
