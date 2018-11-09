package cl.safe.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InformeTrabajadorRequestDto {
	Integer confirmacionPrevencionista;
	Date fechaConfirmacion;
	Date fechaRealizacion;
	Long id;
	String nombre;
	Long prevencionista;
	Long supervisor;
	Long tecnico;
	Long trabajador;
}
