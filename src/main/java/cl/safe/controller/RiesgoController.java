package cl.safe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.InstalacionRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.ExamenEntity;
import cl.safe.entity.RiesgoEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.ExamenService;
import cl.safe.service.RiesgoService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("")
public class RiesgoController {
	@Autowired
	private RiesgoService riesgoService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("/riesgos")
	public ResponseEntity<ResponseDto<List<RiesgoEntity>>> getAll() {
		ResponseDto<List<RiesgoEntity>> rdto = new ResponseDto<>();
		rdto.setObj(riesgoService.findAll());
		rdto.setMessage("OK");
		rdto.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(rdto, HttpStatus.OK);
	}
	
	@PostMapping("/api/riesgos")
	public ResponseEntity<ResponseDto<Long>> getAll(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final RiesgoRequestDto riesgoRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(riesgoService.insertRiesgo(riesgoRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
}
