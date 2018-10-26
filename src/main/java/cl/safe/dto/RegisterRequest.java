package cl.safe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import cl.safe.entity.Profile;

@Getter
@Setter
public class RegisterRequest {

  @NotEmpty
  private String email;

  @NotEmpty
  private String name;

  @NotEmpty
  private String surname;

  @NotEmpty
  private String password;

  private List<Profile> profiles;

}
