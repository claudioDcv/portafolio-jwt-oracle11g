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
	
	@PostMapping
	public ResponseEntity<ResponseDto<List<VisitaMedicaEntity>>> misVisitasMedicasMedico(@RequestAttribute("claims") final Claims claims, @RequestBody @Valid final VisitaMedicaRequestDto visitaMedicaRequestDto) {
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
}
