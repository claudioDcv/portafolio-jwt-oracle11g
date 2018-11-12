package cl.safe.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapacitacionRequestDto {

		Long id;

		Integer asistentesMinimos;

		String nombre;
		
		String descripcion;

		Long examinador;

		Long supervisor;

		Long empresa;
		
		Date fechaRealizacion;
}
