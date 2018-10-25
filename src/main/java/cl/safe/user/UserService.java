package cl.safe.user;

// import static es.rubenjgarcia.user.document.UserPatterns.$User;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.controller.NotFoundException;
import cl.safe.entity.UserEntity;
import cl.safe.security.PasswordUtils;
import cl.safe.user.controller.response.UserJson;
import cl.safe.user.document.User;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public boolean existUser(final String email, String password) {
    final UserEntity user = userRepository.findByEmail(email);
    
    if(user == null ) {
    	return false;
    }
    
    return PasswordUtils.verifyPassword(password, user.getHash());
    
    /*
    return Match(user).of(
        Case($User($(), $(h -> PasswordUtils.verifyPassword(password, h))), (e, h) -> true),
        Case($(), false)
    );
    */
  }

  public UserJson getUserByEmail(final String email) throws NotFoundException {
    final UserEntity user = userRepository.findByEmail(email);
    return Option.of(user)
        .map(UserBuilder::buildUserJson)
        .getOrElseThrow(NotFoundException::new);
  }

  public UserJson registerUser(final String email, final String password, final String name, final String surname) {
    final UserEntity user = UserEntity.builder()
    		.id((long) 1)
        .email(email)
        .name(name)
        .surname(surname)
        .hash(PasswordUtils.createHash(password))
        .build();
    final UserEntity savedUser = userRepository.save(user);
    return UserBuilder.buildUserJson(savedUser);
  }
}
