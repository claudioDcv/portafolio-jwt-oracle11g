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
import cl.safe.dto.ExamenRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.RiesgoRequestDto;
import cl.safe.entity.ExamenEntity;
import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.ExamenService;
import cl.safe.service.InformeService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("")
public class ExamenController {

	@Autowired
	private ExamenService examenService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("/examenes")
	public ResponseEntity<ResponseDto<List<ExamenEntity>>> getAll() {
		ResponseDto<List<ExamenEntity>> rdto = new ResponseDto<>();
		rdto.setObj(examenService.findAll());
		rdto.setMessage("OK");
		rdto.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(rdto, HttpStatus.OK);
	}
	
	@PostMapping("/api/examenes")
	public ResponseEntity<ResponseDto<Long>> getAll(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final ExamenRequestDto examenRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		System.out.println("---> Examenes");
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(examenService.insertExamen(examenRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
}
