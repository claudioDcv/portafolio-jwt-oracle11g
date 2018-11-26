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
import cl.safe.dto.InformeTrabajadorDto;
import cl.safe.dto.InformeTrabajadorRequestDto;
import cl.safe.dto.PaginacionRequestDto;
import cl.safe.dto.PaginacionObjetoResponseDto;
import cl.safe.dto.LoginRequest;
import cl.safe.dto.ObservacionRequestDto;
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
	public ResponseEntity<ResponseDto<Long>> saveInformeTrabajador(@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final InformeTrabajadorRequestDto informeTrabajadorDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.TECNICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.creacionInformeTrabajador(informeTrabajadorDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}

	@PostMapping("/nueva-instalacion")
	public ResponseEntity<ResponseDto<Long>> saveInformeInstalacion(@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final InformeInstalacionDtoRequest informeInstalacionDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.TECNICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.creacionInformeInstalacion(informeInstalacionDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}

	// informe_instalacion_by_id por id de instalacion
	@GetMapping("/instalacion/{id}")
	public ResponseEntity<ResponseDto<InformeInstalacionDto>> getInformeInstalacionById(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.PREVENCIONISTA, Const.TECNICO, Const.SUPERVISOR)) {
			ResponseDto<InformeInstalacionDto> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getInformeInstalacionById(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	// por id de informe trabajador
	@GetMapping("/trabajador/{id}")
	public ResponseEntity<ResponseDto<InformeTrabajadorDto>> getInformeTrabajadorById(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.PREVENCIONISTA, Const.TECNICO, Const.SUPERVISOR)) {
			ResponseDto<InformeTrabajadorDto> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getInformeTrabajadorById(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	// observaciones por id de informe-detalle
	@GetMapping("/observaciones/informe-detalle/{id}")
	public ResponseEntity<ResponseDto<List<ObservacionEntity>>> getObservacionesByInformeDetalleId(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "id") Long id) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.ADMIN_SAFE, Const.PREVENCIONISTA, Const.TECNICO, Const.SUPERVISOR)) {
			ResponseDto<List<ObservacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getObservacionByInformeDetallerId(id));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@PostMapping("/observaciones")
	public ResponseEntity<ResponseDto<Long>> creacionObservacionConInformeId(@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final ObservacionRequestDto observacionRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.TECNICO)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.creacionObservacionConInformeId(observacionRequestDto));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}

	@GetMapping("/trabajador/por-estado/{empresa}/{estado}")
	public ResponseEntity<ResponseDto<List<InformeTrabajadorDto>>> getAllInformesTrabajadorByEstado(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "empresa") Long empresa,
			@PathVariable(name = "estado") Long estado) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA, Const.TECNICO, Const.SUPERVISOR)) {
			ResponseDto<List<InformeTrabajadorDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeTrabajadorByEstado(user.getId(), empresa, estado));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	// ademas filtra por el usuario conectado
	@GetMapping("/instalacion/por-estado/{empresa}/{estado}")
	public ResponseEntity<ResponseDto<List<InformeInstalacionDto>>> getAllInformesInstalacionByEstado(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "empresa") Long empresa,
			@PathVariable(name = "estado") Long estado) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA, Const.TECNICO, Const.SUPERVISOR)) {
			ResponseDto<List<InformeInstalacionDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeInstalacionyEstado(user.getId(), empresa, estado));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@GetMapping("/solicitar-revision-informe-detalle-id/{informeDetalleId}")
	public ResponseEntity<ResponseDto<Boolean>> getAllInformesInstalacionByEstado(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name = "informeDetalleId") Long informeDetalleId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.TECNICO)) {
			ResponseDto<Boolean> rdto = new ResponseDto<>();
			rdto.setObj(informeService.solicitarRevisionInformeDetalle(informeDetalleId, user.getId()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	/* ************************************************/
	@GetMapping("/trabajador/by-supervisor/{idEmpresa}")
	public ResponseEntity<ResponseDto<List<InformeTrabajadorDto>>> getAllInformeTrabajadorBySupervisorId(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "idEmpresa") Long idEmpresa) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.SUPERVISOR)) {
			ResponseDto<List<InformeTrabajadorDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeTrabajadorBySupervisorId(user.getId(), idEmpresa));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@GetMapping("/instalacion/by-supervisor/{idEmpresa}")
	public ResponseEntity<ResponseDto<List<InformeInstalacionDto>>> getAllInformeInstalacionyBySupervisorId(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "idEmpresa") Long idEmpresa) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.SUPERVISOR)) {
			ResponseDto<List<InformeInstalacionDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeInstalacionyBySupervisorId(user.getId(), idEmpresa));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	// asignar prevencionista a informe detalle
	@GetMapping("/asignar-prevencionista/{informeDetalleId}/{prevencionistaId}")
	public ResponseEntity<ResponseDto<Long>> asignarPrevencionista(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name = "informeDetalleId") Long informeDetalleId,
			@PathVariable(name = "prevencionistaId") Long prevencionistaId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		UserEntity prevencionista = userServiceSP.findById(prevencionistaId);

		if (!Utils.hasProfile(prevencionista, Const.PREVENCIONISTA)) {
			return Utils.responseUnauthorized("Prevencionista debe poseer perfil prevencionista");
		}
		if (Utils.hasProfile(user, Const.SUPERVISOR)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.asignarPrevencionista(informeDetalleId, prevencionistaId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@GetMapping("/aprobar/{informeDetalleId}")
	public ResponseEntity<ResponseDto<Long>> aprobarInformeDetalle(@RequestAttribute("claims") final Claims claims,
			@PathVariable(name = "informeDetalleId") Long informeDetalleId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.aprobarInformeDetalle(informeDetalleId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@GetMapping("/rechazar/{informeDetalleId}")
	public ResponseEntity<ResponseDto<Long>> rechazarInformeDetalle(@RequestAttribute("claims") final Claims claims,
			@PathVariable(name = "informeDetalleId") Long informeDetalleId) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.rechazarInformeDetalle(informeDetalleId));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	// prevencionista

	// ademas filtra por el usuario conectado
	@GetMapping("/instalacion/prevencionista/{empresa}/{estado}")
	public ResponseEntity<ResponseDto<List<InformeInstalacionDto>>> getAllInformeInstalacionyByEstadoPrevencionistaId(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "empresa") Long empresa,
			@PathVariable(name = "estado") Long estado) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA)) {
			ResponseDto<List<InformeInstalacionDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeInstalacionyByEstadoPrevencionistaId(
					user.getId(),
					empresa,
					estado));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}

	@GetMapping("/trabajador/prevencionista/{empresa}/{estado}")
	public ResponseEntity<ResponseDto<List<InformeTrabajadorDto>>> getAllInformeTrabajadorByEstadoPrevencionistaId(
			@RequestAttribute("claims") final Claims claims, @PathVariable(name = "empresa") Long empresa,
			@PathVariable(name = "estado") Long estado) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA)) {
			ResponseDto<List<InformeTrabajadorDto>> rdto = new ResponseDto<>();
			rdto.setObj(informeService.getAllInformeTrabajadorByEstadoPrevencionistaId(
					user.getId(),
					empresa,
					estado));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}

		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/observaciones/recomendaciones")
	public ResponseEntity<ResponseDto<Long>> agregarRecomendacionParaObservacionPorPreve(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final ObservacionRequestDto observacionRequestDto) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(user, Const.PREVENCIONISTA)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(informeService.agregarRecomendacionParaObservacionPorPreve(
					observacionRequestDto.getRecomendacion(),
					observacionRequestDto.getId()));
			rdto.setMessage("OK");
			rdto.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
		return Utils.responseUnauthorized();
	}
	
	// INFORMES PARA ADMIN DE EMPRESA /api/informes/instalacion-admin-empresa
	// informe_instalacion_by_id por id de instalacion
		@GetMapping("/instalacion-admin-empresa")
		public ResponseEntity<ResponseDto<List<InformeInstalacionDto>>> getAllInformeInstalacionADMINEMPRESA(
				@RequestAttribute("claims") final Claims claims) {
			UserEntity user = userServiceSP.findByEmail(claims.getSubject());
			
			if (user.getEmpresaFk() == null) {
				return Utils.responseUnauthorized("No registra empresa asignada como Administrador");
			}
			
			if (Utils.hasProfile(user, Const.ADMIN_EMPRESA)) {
				ResponseDto<List<InformeInstalacionDto>> rdto = new ResponseDto<>();
				rdto.setObj(informeService.getAllInformeInstalacionADMINEMPRESA(user.getEmpresaFk()));
				rdto.setMessage("OK");
				rdto.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(rdto, HttpStatus.OK);
			}

			return Utils.responseUnauthorized();
		}

		// por id de informe trabajador /api/informes/trabajador-admin-empresa
		@GetMapping("/trabajador-admin-empresa")
		public ResponseEntity<ResponseDto<List<InformeTrabajadorDto>>> getAllInformeTrabajadorADMINEMPRESA(
				@RequestAttribute("claims") final Claims claims) {
			UserEntity user = userServiceSP.findByEmail(claims.getSubject());
			
			if (user.getEmpresaFk() == null) {
				return Utils.responseUnauthorized("No registra empresa asignada como Administrador");
			}
			
			if (Utils.hasProfile(user, Const.ADMIN_EMPRESA)) {
				ResponseDto<List<InformeTrabajadorDto>> rdto = new ResponseDto<>();
				rdto.setObj(informeService.getAllInformeTrabajadorADMINEMPRESA(user.getEmpresaFk()));
				rdto.setMessage("OK");
				rdto.setStatus(HttpStatus.OK);
				return new ResponseEntity<>(rdto, HttpStatus.OK);
			}

			return Utils.responseUnauthorized();
		}
		
		// por id de informe trabajador /api/informes/trabajador-admin-empresa
				@PostMapping("/trabajador-admin-empresa")
				public ResponseEntity<ResponseDto<PaginacionObjetoResponseDto>> getAllInformeTrabajadorADMINEMPRESA_PAG(
						@RequestAttribute("claims") final Claims claims,
						final @RequestBody PaginacionRequestDto instaParam) {
					UserEntity user = userServiceSP.findByEmail(claims.getSubject());
					
					if (user.getEmpresaFk() == null) {
						return Utils.responseUnauthorized("No registra empresa asignada como Administrador");
					}

					if (Utils.hasProfile(user, Const.ADMIN_EMPRESA)) {
						ResponseDto<PaginacionObjetoResponseDto> rdto = new ResponseDto<>();
						rdto.setObj(informeService.getAllInformeTrabajadorADMINEMPRESA_PAG(
								user.getEmpresaFk(),
								instaParam.getPageNumber(),
								instaParam.getPageSize(),
								instaParam.getFromDate(),
								instaParam.getToDate()
								));
						rdto.setMessage("OK");
						rdto.setStatus(HttpStatus.OK);
						return new ResponseEntity<>(rdto, HttpStatus.OK);
					}

					return Utils.responseUnauthorized();
				}
}
