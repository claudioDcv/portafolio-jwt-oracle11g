package cl.safe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaRequestDto {
	Long id;
	Long trabajadorId;
	Long capacitacion;
	String firmar;
}
