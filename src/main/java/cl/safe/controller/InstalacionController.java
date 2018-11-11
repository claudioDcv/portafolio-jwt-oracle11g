package cl.safe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.InstalacionRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.InstalacionService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/instalaciones")
public class InstalacionController {

	@Autowired
	InstalacionService instalacionService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("")
	public ResponseEntity<ResponseDto<List<InstalacionEntity>>> getAll(@RequestAttribute("claims") final Claims claims) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.EXAMINADOR,
				Const.MEDICO,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<InstalacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(instalacionService.getAllSP());
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/empresa/{id}")
	public ResponseEntity<ResponseDto<List<InstalacionEntity>>> getByEmpresaId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.EXAMINADOR,
				Const.MEDICO,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<InstalacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(instalacionService.getAllByEmpresaId(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> crearInstalacion(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final InstalacionRequestDto instalacionRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(instalacionService.crearInstalacion(instalacionRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
}
