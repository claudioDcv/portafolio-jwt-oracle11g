package cl.safe.service;

import io.vavr.control.Option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.safe.controller.NotFoundException;
import cl.safe.dto.UserJson;
import cl.safe.entity.Profile;
import cl.safe.entity.UserEntity;
import cl.safe.repository.UserRepository;
import cl.safe.security.PasswordUtils;
import cl.safe.user.UserBuilder;

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
  }

  public UserJson getUserByEmail(final String email) throws NotFoundException {
    final UserEntity user = userRepository.findByEmail(email);
    return Option.of(user)
        .map(UserBuilder::buildUserJson)
        .getOrElseThrow(NotFoundException::new);
  }

  public UserJson registerUser(final String email, final String password, final String name, final String surname, List<Profile> profiles) {
    final UserEntity user = UserEntity.builder()
        .email(email)
        .name(name)
        .surname(surname)
        .hash(PasswordUtils.createHash(password))
        .profiles(profiles)
        .build();
    final UserEntity savedUser = userRepository.save(user);
    return UserBuilder.buildUserJson(savedUser);
  }
  
  public UserEntity findOne(Long id) {
	  return userRepository.findOne(id);
  }
  
}
