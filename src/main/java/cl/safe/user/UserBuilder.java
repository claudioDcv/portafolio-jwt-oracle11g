package cl.safe.user;

import cl.safe.entity.UserEntity;
import cl.safe.user.controller.response.UserJson;
import cl.safe.user.document.User;

public interface UserBuilder {

  static UserJson buildUserJson(final UserEntity user) {
    return UserJson.builder()
        .email(user.getEmail())
        .name(user.getName())
        .surname(user.getSurname())
        .build();
  }
}
