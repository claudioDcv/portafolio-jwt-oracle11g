package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="INFORMES_TRABAJADOR")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformeTrabajadorEntity {
	
	@Id
	@Column(name="INFORME_TRABAJADOR_ID")
	Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informe_detalle_fk")
	InformeDetalleEntity informeDetalle;
	
	@ManyToOne
    @JoinColumn(name = "trabajador_fk")
	TrabajadorEntity trabajador;
}
