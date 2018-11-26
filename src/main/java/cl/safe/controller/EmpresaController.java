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
import cl.safe.dto.ResponseDto;
import cl.safe.dto.SearchDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.EmpresaService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("")
	public ResponseEntity<ResponseDto<List<EmpresaEntity>>> getAll(@RequestAttribute("claims") final Claims claims) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.EXAMINADOR,
				Const.MEDICO,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<EmpresaEntity>> rdto = new ResponseDto<>();
			rdto.setObj(empresaService.getAllSP());
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<EmpresaEntity>> finOne(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.EXAMINADOR,
				Const.MEDICO,
				Const.TECNICO,
				Const.SUPERVISOR) || (
			Utils.hasProfile(user, Const.ADMIN_EMPRESA) && user.getEmpresaFk() == id
		)) {
			ResponseDto<EmpresaEntity> rdto = new ResponseDto<>();
			rdto.setObj(empresaService.findOne(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> crearEmpresa(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final EmpresaEntity empresaEntity) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(empresaService.crearEmpresa(empresaEntity));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}

}
