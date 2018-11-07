package cl.safe.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import cl.safe.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserJson{

	private Long id;
	private String email;
	private String name;
	private String surname;
	private List<Profile> profiles;
}
