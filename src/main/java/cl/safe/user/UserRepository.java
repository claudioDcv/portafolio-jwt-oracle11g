package cl.safe.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.safe.entity.UserEntity;
import cl.safe.user.document.User;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByEmail(String email);
}
