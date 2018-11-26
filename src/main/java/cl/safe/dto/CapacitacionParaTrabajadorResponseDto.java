package cl.safe.dto;

import java.util.Date;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapacitacionParaTrabajadorResponseDto {
	Date fechaRealizacion;
	String capacitacionNombre;
	Long capacitacionId;
	
	Long id;
	String nombre;
	String apellidoMaterno;
	String apellidoPaterno;
	String run;
	String email;
	
	String nombreEmpresa;
	Long empresaId;
	
	Long asistenciaId;
}
