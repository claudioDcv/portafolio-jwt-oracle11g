package cl.safe.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitaMedicaRequestDto {
	Long id;
	Long medicoId;
	Long supervisorId;
	Integer confirmacion;
	Long empresaId;
	Date fechaRealizacion;
	Integer estado;
}
