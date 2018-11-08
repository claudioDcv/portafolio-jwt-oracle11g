package cl.safe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="INFORMES_INSTALACION")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformeInstalacionEntity {
	
	@Id
	@Column(name="INFORME_INSTALACION_ID")
	Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "informe_detalle_fk")
	InformeDetalleEntity informeDetalle;
	
	@ManyToOne
    @JoinColumn(name = "instalacion_fk")
	InstalacionEntity instalacion;
}
