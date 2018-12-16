package cl.safe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDtoResponse {
	Long asistenciaId;
	Long trabajadorId;
	Long capacitacionId;
	String nombre;
	String apellidoPaterno;
	String apellidoMaterno;
	String email;
	String run;
	String firma;
	String firmaOriginal;
}
