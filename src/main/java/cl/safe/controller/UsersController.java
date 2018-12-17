package cl.safe.controller;

import io.jsonwebtoken.Claims;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.ProfilesRequestDto;
import cl.safe.dto.RegisterRequest;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.UserJson;
import cl.safe.dto.UserUpdateRequestDto;
import cl.safe.entity.UserEntity;
import cl.safe.service.ProfileService;
import cl.safe.service.UserService;
import cl.safe.service.UserServiceSP;

@RestController
@RequestMapping("/api/usuarios")
public class UsersController {

	@Autowired
	private UserServiceSP userServiceSP;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("")
	public ResponseEntity<ResponseDto<List<UserEntity>>> findAll(@RequestAttribute("claims") final Claims claims) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.SUPERVISOR)) {
			
			final List<UserEntity> usuariosEstandarizados = userServiceSP.getAllUsers();
			
			usuariosEstandarizados.forEach(new Consumer<UserEntity>() {

				@Override
				public void accept(UserEntity t) {
					if (t.getEmpresaFk() == null) {
						t.setEmpresaFk((long)0);
					}
				}
			});
			
			
			ResponseDto<List<UserEntity>> rdto = new ResponseDto<>();
			rdto.setObj(usuariosEstandarizados);
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<UserEntity>> getById(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.SUPERVISOR)) {
			ResponseDto<UserEntity> rdto = new ResponseDto<>();
			UserEntity usuario = userServiceSP.findById(id);
			if (usuario.getEmpresaFk() == null) {
				usuario.setEmpresaFk((long)0);
			}
			rdto.setObj(usuario);
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/perfiles/{id}")
	public ResponseEntity<ResponseDto<List<UserEntity>>> getAllByProfileId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.SUPERVISOR, Const.EXAMINADOR, Const.MEDICO, Const.PREVENCIONISTA, Const.TECNICO)) {
			ResponseDto<List<UserEntity>> rdto = new ResponseDto<>();
			rdto.setObj(userServiceSP.usersByProfileId(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}

	@GetMapping("/profile")
	public ResponseEntity<ResponseDto<UserJson>> getProfile(@RequestAttribute("claims") final Claims claims) throws NotFoundException {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		UserJson uJson = UserJson.builder().email(u.getEmail()).name(u.getName()).surname(u.getSurname())
				.empresaFk(u.getEmpresaFk()).profiles(u.getProfiles()).id(u.getId()).build();
		
		if(uJson != null) {
			ResponseDto<UserJson> rdto = new ResponseDto<>();
			rdto.setObj(uJson);
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		
		return Utils.responseUnauthorized();
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto<Long>> getAllByProfileId(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final RegisterRequest registerRequest) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(userServiceSP.saveSP(registerRequest));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/asignar-perfiles")
	public ResponseEntity<ResponseDto<Long>> asignarPerfiles(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final ProfilesRequestDto profilesRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		// solo admin puede cambiar y ademas un admin no puede cambiarce sus permisos, lo debe hacer otro admin
		if (Utils.hasProfile(user, Const.ADMIN_SAFE) && user.getId() != profilesRequestDto.getUsuario()) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(profileService.asignarPerfilesConUsuarioId(profilesRequestDto.getPerfiles(), profilesRequestDto.getUsuario()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		
		if (user.getId() == profilesRequestDto.getUsuario()) {
			return Utils.responseUnauthorized("Usted no puede cambiar sus propios permisos, otro administrador debe realizar esta acción");
		}
		return Utils.responseUnauthorized();
	}
	
	// update de medico
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDto<Long>> actualizarUsuario(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id, @RequestBody @Valid final UserEntity updateUser) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();

			updateUser.setId(id);
			
			rdto.setObj(userServiceSP.updateSP(updateUser));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
}
