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
import cl.safe.dto.ProfilesRequestDto;
import cl.safe.dto.RegisterRequest;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.SearchDto;
import cl.safe.dto.TrabajadorRequestDto;
import cl.safe.dto.TrabajadorRiesgosRequestDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.TrabajadorEntity;
import cl.safe.entity.UserEntity;
import cl.safe.repository.TrabajadorRepository;
import cl.safe.service.TrabajadorService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {
	
	@Autowired
	TrabajadorService trabajadorService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@GetMapping("")
	List<TrabajadorEntity> findAll() {
		return (List<TrabajadorEntity>) trabajadorService.findAll();
	}

	@GetMapping("/{id}")
	public TrabajadorEntity getById(@PathVariable(name="id") Long id) {
		return trabajadorService.findOne(id);
	}
	
	@PostMapping("/buscar-por-email")
	public TrabajadorEntity getById(@RequestBody @Valid final SearchDto searchDto) {
		return trabajadorService.findByEmail(searchDto.getEmail());
	}
	
	@GetMapping("/empresa/{id}")
	public ResponseEntity<ResponseDto<List<TrabajadorEntity>>> getByEmpresaId(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.EXAMINADOR,
				Const.MEDICO,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<TrabajadorEntity>> rdto = new ResponseDto<>();
			rdto.setObj(trabajadorService.getByEmpresaIdSP(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> crearTrabajador(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final TrabajadorRequestDto tra) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if ( trabajadorService.getTrabajadorByRutAndEmpresaId(tra.getRun(), tra.getEmpresa()) > 0 ) {
			return Utils.responseUnauthorized("Trabajador con este rut ya existe en esta empresa.");
		}
		
		
		if (
				tra.getApellidoMaterno().isEmpty() || 
				tra.getApellidoPaterno().isEmpty() || 
				tra.getEmail().isEmpty() || 
				tra.getEmpresa() == null || 
				tra.getNombre().isEmpty() || 
				tra.getRun().isEmpty()) {
			return Utils.responseUnauthorized("Todos los datos son requeridos.");
		}
		
		if (
				tra.getApellidoMaterno() == null || 
				tra.getApellidoPaterno() == null || 
				tra.getEmail() == null || 
				tra.getEmpresa() == null || 
				tra.getNombre() == null || 
				tra.getRun() == null) {
			return Utils.responseUnauthorized("Todos los datos son requeridos.");
		}
		
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(trabajadorService.crearTrabajadorSP(tra));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/asignar-riesgos")
	public ResponseEntity<ResponseDto<Long>> asignarPerfiles(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final TrabajadorRiesgosRequestDto trabajadorRiesgosRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user, Const.ADMIN_SAFE)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(trabajadorService.asignarRiesgosConTrabajadorId(
					trabajadorRiesgosRequestDto.getRiesgos(),
					trabajadorRiesgosRequestDto.getTrabajadorId()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/en-empresa-con-riesgo/{empresaId}")
	public ResponseEntity<ResponseDto<List<TrabajadorEntity>>> findAllTrabajadoresRiesgoByEmpresaId(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="empresaId") Long empresaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.MEDICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<TrabajadorEntity>> rdto = new ResponseDto<>();
			rdto.setObj(trabajadorService.findAllTrabajadoresRiesgoByEmpresaId(empresaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
}
