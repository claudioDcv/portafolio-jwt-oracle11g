package es.rubenjgarcia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import es.rubenjgarcia.entity.UserEntity;

@Service
public interface UserServiceSP {

	public List<UserEntity> findAll(); 
	
	List<UserEntity> getAllUsers();
	
	UserEntity findById(Long id);
	
	@Transactional
	Long delete (Long id);
	
	Long updateSP(UserEntity user);
}
