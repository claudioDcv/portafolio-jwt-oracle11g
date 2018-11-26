package cl.safe.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaMedicaParaTrabajadorRequestDto {
	
	String run;
	Long empresaId;
	
	Long consultaId;
}
