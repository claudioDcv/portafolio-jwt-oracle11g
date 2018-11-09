package cl.safe.service;

import java.util.List;

import cl.safe.entity.Profile;

public interface ProfileService {
	List<Profile> getProfileByUserId(Long id);

	List<Profile> getProfileUserByUserId(Long id);

	List<Profile> getAllProfiles();
	
	Long asignarPerfilesConUsuarioId(List<Long> perfiles, Long usuarioId );
}
