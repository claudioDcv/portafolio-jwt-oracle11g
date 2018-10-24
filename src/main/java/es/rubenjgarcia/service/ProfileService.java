package es.rubenjgarcia.service;

import java.util.List;

import es.rubenjgarcia.entity.Profile;

public interface ProfileService {
	List<Profile> getProfileByUserId(Long id);

	List<Profile> getProfileUserByUserId(Long id);

	List<Profile> getAllProfiles();
}
