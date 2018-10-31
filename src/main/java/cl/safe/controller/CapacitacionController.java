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
import cl.safe.dto.CapacitacioneRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.VisitaMedicaRequestDto;
import cl.safe.entity.AsistenciaUsuarioEntity;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.entity.VisitaMedicaEntity;
import cl.safe.service.CapacitacionService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/capacitaciones")
public class CapacitacionController {

	@Autowired
	CapacitacionService capacitacionService;
	
	@Autowired
	private UserServiceSP userServiceSP;
	
	@PostMapping
	public ResponseEntity<ResponseDto<List<CapacitacionEntity>>> misVisitasMedicasMedico(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final CapacitacioneRequestDto capacitacioneRequestDto) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.ADMIN_SAFE, Const.EXAMINADOR, Const.SUPERVISOR)) {
			ResponseDto<List<CapacitacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.findAllByEmpresaExaminadorSP(capacitacioneRequestDto.getEmpresaId(), u.getId()));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<CapacitacionEntity>> getById(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.EXAMINADOR, Const.SUPERVISOR)) {
			ResponseDto<CapacitacionEntity> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.findOneSP(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/asistencias/{id}")
	public ResponseEntity<ResponseDto<List<AsistenciaUsuarioEntity>>> asistenciasByCapacitacionId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.ADMIN_SAFE, Const.EXAMINADOR, Const.SUPERVISOR)) {
			ResponseDto<List<AsistenciaUsuarioEntity>> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.findAllAsistentesByCapacitacionId(id));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
}
