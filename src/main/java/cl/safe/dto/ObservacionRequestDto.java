package cl.safe.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class ObservacionRequestDto {
	Long id;
	Long informeId;
	String nombre;
	String recomendacion;
}
