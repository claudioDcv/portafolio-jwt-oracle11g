package cl.safe.dto;

import java.util.Date;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrabajadorRequestDto {
	Long id;
	String apellidoMaterno;
	String apellidoPaterno;
	String email;
	Long empresa;
	String nombre;
	String run;
}