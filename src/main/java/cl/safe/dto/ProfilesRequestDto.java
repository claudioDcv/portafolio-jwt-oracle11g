package cl.safe.dto;

import java.util.Date;
import java.util.List;

import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfilesRequestDto {
	Long usuario;
	List<Long> perfiles;
}