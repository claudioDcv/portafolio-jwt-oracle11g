package cl.safe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.AsistenciaRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.AsistenciaService;
import cl.safe.service.CapacitacionService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/asistencias")
public class AsisteniciasController {
	
	@Autowired
	AsistenciaService asistenciaService;
	
	@Autowired
	CapacitacionService capacitacionService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> inscribirEnCapacitacion(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final AsistenciaRequestDto asistenciaRequestDto) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		
		Integer estadoCapacitacion = 1;
		Long capacitacionId = asistenciaRequestDto.getCapacitacion();
		if (capacitacionId != null) {
			CapacitacionEntity cap = capacitacionService.findOneSP(capacitacionId);
			if (cap != null) {
				estadoCapacitacion = cap.getEstado();
			} else {
				return Utils.responseUnauthorized("Capacitación no existe");
			}
		} else {
			return Utils.responseUnauthorized("Falta id capacitación");
		}
		
		if (estadoCapacitacion != 0) {
			return Utils.responseUnauthorized("No se puede inscribir, esta capacitacion se he cerrado");
		}
		
		if (Utils.hasProfile(u, Const.SUPERVISOR) && estadoCapacitacion == 0) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(asistenciaService.inscribirEnCapacitacion(
					asistenciaRequestDto.getTrabajadorId(),
					asistenciaRequestDto.getCapacitacion()));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/firmar")
	public ResponseEntity<ResponseDto<Long>> firmarAsistencia(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final AsistenciaRequestDto asistenciaRequestDto) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(u, Const.EXAMINADOR)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(asistenciaService.firmarAsistencia(asistenciaRequestDto.getId(), asistenciaRequestDto.getFirmar()));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
}
