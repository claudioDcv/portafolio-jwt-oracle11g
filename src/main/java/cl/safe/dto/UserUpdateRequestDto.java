package cl.safe.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
	
	@NotEmpty
	private Long id;
	
	@NotEmpty
	private String email;

	@NotEmpty
	private String name;

	@NotEmpty
	private String surname;

	@NotEmpty
	private String password;

}
