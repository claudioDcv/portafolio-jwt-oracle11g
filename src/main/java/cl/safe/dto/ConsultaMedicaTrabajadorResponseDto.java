package cl.safe.dto;

import java.util.Date;

import javax.persistence.Id;

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
public class ConsultaMedicaTrabajadorResponseDto {
	Long id;
	Long trabajadorId;
	Long empresaFk;
	String run;
	Date fechaRealizacion;
	Long visitaMedicaId;
}
