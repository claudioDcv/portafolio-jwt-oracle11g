package cl.safe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cl.safe.entity.UserEntity;

@Service
public interface UserServiceSP {

	public List<UserEntity> findAll(); 
	
	List<UserEntity> getAllUsers();
	
	UserEntity findById(Long id);
	
	UserEntity findByEmail(String email);
	
	@Transactional
	Long delete (Long id);
	
	Long updateSP(UserEntity user);
	
	List<UserEntity> usersByProfileId(Long profileId);
}
