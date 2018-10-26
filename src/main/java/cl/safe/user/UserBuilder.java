package cl.safe.user;

import cl.safe.dto.UserJson;
import cl.safe.entity.UserEntity;

public interface UserBuilder {

  static UserJson buildUserJson(final UserEntity user) {
    return UserJson.builder()
        .email(user.getEmail())
        .name(user.getName())
        .surname(user.getSurname())
        .profiles(user.getProfiles())
        .build();
  }
}
