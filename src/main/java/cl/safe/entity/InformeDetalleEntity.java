package cl.safe.entity;

import java.util.Date;

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

@Table(name="INFORMES_DETALLES")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformeDetalleEntity {
	
	@Id
	@Column(name="INFORME_DETALLE_ID")
	Long id;
	
	@ManyToOne
    @JoinColumn(name = "prevencionista_fk")
	UserEntity prevencionista;
	
	@ManyToOne
    @JoinColumn(name = "supervisor_fk")
	UserEntity supervisor;
	
	@ManyToOne
    @JoinColumn(name = "tecnico_fk")
	UserEntity tecnico;
	
	@Column(name = "nombre")
	String nombre;
	
	@Column(name="fecha_realizacion")
	Date fechaRealizacion;
	
	@Column(name="fecha_confirmacion")
	Date fechaConfirmacion;

	@Column(name="confirmacion_prevencionista")
	Boolean confirmacionPrevencionista; 
}
