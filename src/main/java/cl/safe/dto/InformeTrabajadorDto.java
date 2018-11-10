package cl.safe.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.TrabajadorEntity;
import cl.safe.entity.UserEntity;
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
public class InformeTrabajadorDto {
	
	@Id
	Long id;
	
	Long detalle;

	@ManyToOne
    @JoinColumn(name = "trabajador")
	TrabajadorEntity trabajador;
	
	Integer confirmacionPrevencionista;

	Date fechaConfirmacion;
	Date fechaRealizacion;

	String nombre;
	Long prevencionista;
	
	@ManyToOne
    @JoinColumn(name = "supervisor")
	UserEntity supervisor;
	Long tecnico;
	
}
