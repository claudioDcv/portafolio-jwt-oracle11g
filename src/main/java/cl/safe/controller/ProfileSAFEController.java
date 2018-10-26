package cl.safe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.dto.ResponseDto;
import cl.safe.entity.Profile;
import cl.safe.service.ProfileService;

@RestController
@RequestMapping(value="/api/perfil")
public class ProfileSAFEController {

	@Autowired
	private ProfileService profileService;
	
	@CrossOrigin
	@GetMapping(value="/por-id-usuario/{id}")
	public ResponseDto<List<Profile>> getProfileByUserId(@PathVariable(name="id") Long id) {
		ResponseDto<List<Profile>> responseDto = new ResponseDto<>();
		responseDto.setObject(profileService.getProfileByUserId(id));
		return responseDto;
	}
	
	@CrossOrigin
	@GetMapping("")
	public ResponseDto<List<Profile>> findAll() {
		ResponseDto<List<Profile>> responseDto = new ResponseDto<>();
		responseDto.setObject(profileService.getAllProfiles());
		return responseDto;
	}
}
