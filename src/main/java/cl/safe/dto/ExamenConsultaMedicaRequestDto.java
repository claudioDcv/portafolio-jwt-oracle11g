package cl.safe.dto;

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
public class ExamenConsultaMedicaRequestDto {
	Long id;
	Long examenId;
	Long consultaMedicaId;
}
