package cl.safe.dto;

import java.util.Date;

import javax.persistence.Entity;

import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.UserEntity;
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
public class RiesgoRequestDto {
	String nombre;
	String codigo;
	Long id;
}
