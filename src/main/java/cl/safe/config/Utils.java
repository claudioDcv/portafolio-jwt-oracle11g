package cl.safe.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.safe.dto.ResponseDto;
import cl.safe.entity.Profile;
import cl.safe.entity.UserEntity;

public class Utils {
	public static final Boolean hasAdminSafe(UserEntity user) {
		Boolean hasAdmin = Boolean.FALSE;

		for (Profile profile : user.getProfiles()) {
			if (profile.getNaturalKey().equals(Const.ADMIN_SAFE)) {
				hasAdmin = Boolean.TRUE;
			}
		}
		return hasAdmin;

	}

	public static final Boolean hasProfile(UserEntity user, String... profiles) {
		Boolean hasValidProfile = Boolean.FALSE;
		System.out.println("---> hasProfile");
		for (Profile profile : user.getProfiles()) {
			System.out.println("----> " + profile.getDisplayName());
			for (String compareProf : profiles) {
				if (profile.getNaturalKey().equals(compareProf)) {
					hasValidProfile = Boolean.TRUE;
				}
			}

		}
		return hasValidProfile;

	}
	
	public static final ResponseEntity responseUnauthorized() {
		ResponseDto responseDto = new ResponseDto<>(Const.UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED, null, 401);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.UNAUTHORIZED);
	}
	
	public static final ResponseEntity responseUnauthorized(String mensaje) {
		ResponseDto responseDto = new ResponseDto<>(mensaje, HttpStatus.UNAUTHORIZED, null, 401);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.UNAUTHORIZED);
	}
}
