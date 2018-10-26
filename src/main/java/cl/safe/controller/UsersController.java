package cl.safe.controller;

import io.jsonwebtoken.Claims;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.UserJson;
import cl.safe.entity.UserEntity;
import cl.safe.service.UserServiceSP;

@RestController
@RequestMapping("/api/usuarios")
public class UsersController {

	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("")
	public ResponseEntity<ResponseDto<List<UserEntity>>> findAll(@RequestAttribute("claims") final Claims claims) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.PREVENCIONISTA)) {
			ResponseDto<List<UserEntity>> rdto = new ResponseDto<>();
			rdto.setObj(userServiceSP.getAllUsers());
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<UserEntity>> getById(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.PREVENCIONISTA)) {
			ResponseDto<UserEntity> rdto = new ResponseDto<>();
			rdto.setObj(userServiceSP.findById(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}

	@GetMapping("/profile")
	public UserJson getProfile(@RequestAttribute("claims") final Claims claims) throws NotFoundException {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		return UserJson.builder().email(u.getEmail()).name(u.getName()).surname(u.getSurname())
				.profiles(u.getProfiles()).build();
	}
}
