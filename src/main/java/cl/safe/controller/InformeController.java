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
import cl.safe.dto.InformeInstalacionDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.dto.LoginRequest;
import cl.safe.dto.ResponseDto;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.InstalacionEntity;
import cl.safe.entity.ObservacionEntity;
import cl.safe.entity.TrabajadorEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.EmpresaService;
import cl.safe.service.InformeService;
import cl.safe.service.InstalacionService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/informes")
public class InformeController {

	@Autowired
	InformeService informeService;
	
	@Autowired
	private UserServiceSP userServiceSP;

	@PostMapping("/nuevo-trabajador")
	public ResponseEntity<ResponseDto<Long>> saveInformeTrabajador(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final InformeTrabajadorRequestDto informeTrabajadorDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user,
				Const.TECNICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.creacionInformeTrabajador(informeTrabajadorDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}	
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/nueva-instalacion")
	public ResponseEntity<ResponseDto<Long>> saveInformeInstalacion(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final InformeInstalacionDtoRequest informeInstalacionDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user,
				Const.TECNICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.creacionInformeInstalacion(informeInstalacionDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}	
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/instalacion/{id}")
	public ResponseEntity<ResponseDto<InformeInstalacionDto>> getByEmpresaId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<InformeInstalacionDto> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getInformeInstalacionById(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/observaciones/informe-detalle/{id}")
	public ResponseEntity<ResponseDto<List<ObservacionEntity>>> getObservacionesByInformeDetalleId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (Utils.hasProfile(user,
				Const.ADMIN_SAFE,
				Const.PREVENCIONISTA,
				Const.TECNICO,
				Const.SUPERVISOR)) {
			ResponseDto<List<ObservacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getObservacionByInformeDetallerId(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
}
