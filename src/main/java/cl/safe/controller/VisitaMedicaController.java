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
import cl.safe.dto.ConsultaMedicaRequestDto;
import cl.safe.dto.ExamenConsultaMedicaRequestDto;
import cl.safe.dto.InstalacionRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.VisitaMedicaRequestDto;
import cl.safe.entity.UserEntity;
import cl.safe.entity.VisitaMedicaEntity;
import cl.safe.service.UserServiceSP;
import cl.safe.service.VisitaMedicaService;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/visitas-medicas")
public class VisitaMedicaController {
	
	@Autowired
	VisitaMedicaService visitaMedicaService;
	
	@Autowired
	private UserServiceSP userServiceSP;
	
	@PostMapping("/mis-visitas-medicas-medico")
	public ResponseEntity<ResponseDto<List<VisitaMedicaEntity>>> misVisitasMedicasMedico(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final VisitaMedicaRequestDto visitaMedicaRequestDto) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.ADMIN_SAFE, Const.MEDICO, Const.SUPERVISOR)) {
			ResponseDto<List<VisitaMedicaEntity>> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.findAllByEmpresaMedicoConfirmacionSP(visitaMedicaRequestDto.getEmpresaId(), u.getId(), visitaMedicaRequestDto.getConfirmacion()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();

	}
	
	@GetMapping("/mis-visitas-medicas-supervisor/{empresaId}")
	public ResponseEntity<ResponseDto<List<VisitaMedicaEntity>>> misVisitasMedicasSupervisor(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="empresaId") Long empresaId) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.SUPERVISOR)) {
			ResponseDto<List<VisitaMedicaEntity>> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.findAllByEmpresaSupervisorSP(empresaId, u.getId()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}

	/*
	 * List<VisitaMedicaEntity> findAll();
	 * ExamenConsultaMedicaRequestDto
	Long aceptarVisitaMedica(Long visitaMedicaId);
	Long rechazarVisitaMedica(Long visitaMedicaId);
	 */
	
	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> crearVisitaMedica(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final VisitaMedicaRequestDto visitaMedicaRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.SUPERVISOR)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.crearVisitaMedica(visitaMedicaRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/cerrar/{visitaMedicaId}")
	public ResponseEntity<ResponseDto<Long>> cerrarVisitaMedica(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="visitaMedicaId") Long visitaMedicaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO, Const.SUPERVISOR)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.cerrarVisitaMedica(visitaMedicaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/aceptar/{visitaMedicaId}")
	public ResponseEntity<ResponseDto<Long>> aceptarVisitaMedica(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="visitaMedicaId") Long visitaMedicaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.aceptarVisitaMedica(visitaMedicaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/rechazar/{visitaMedicaId}")
	public ResponseEntity<ResponseDto<Long>> rechazarVisitaMedica(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="visitaMedicaId") Long visitaMedicaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.rechazarVisitaMedica(visitaMedicaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/consulta-medica")
	public ResponseEntity<ResponseDto<Long>> crearConsultaMedica(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final ConsultaMedicaRequestDto consultaMedicaRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.crearConsultaMedica(consultaMedicaRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/consulta-medica/cerrar/{consultaMedicaId}")
	public ResponseEntity<ResponseDto<Long>> cerrarConsultaMedica(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="consultaMedicaId") Long consultaMedicaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.cerrarConsultaMedica(consultaMedicaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/asignar-examen-consulta-medica")
	public ResponseEntity<ResponseDto<Long>> asignarExamenConsultaMedica(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final ExamenConsultaMedicaRequestDto examenConsultaMedicaRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.MEDICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(visitaMedicaService.asignarExamenConsultaMedica(
					examenConsultaMedicaRequestDto.getConsultaMedicaId(),
					examenConsultaMedicaRequestDto.getExamenId()
			));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
}
