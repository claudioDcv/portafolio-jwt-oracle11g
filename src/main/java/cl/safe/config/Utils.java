package cl.safe.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
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
		Boolean hasAdmin = Boolean.FALSE;
		for (Profile profile : user.getProfiles()) {
			for (String compareProf : profiles) {
				if (profile.getNaturalKey().equals(compareProf)) {
					hasAdmin = Boolean.TRUE;
				}
			}

		}
		return hasAdmin;

	}
	
	public static final ResponseEntity responseUnauthorized() {
		ResponseDto responseDto = new ResponseDto<>(Const.UNAUTHORIZED_MESSAGE, HttpStatus.UNAUTHORIZED, null, 401);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.UNAUTHORIZED);
	}
}
