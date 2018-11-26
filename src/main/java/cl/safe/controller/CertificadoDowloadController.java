package cl.safe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.CapacitacionParaTrabajadorRequestDto;
import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.dto.ConsultaMedicaParaTrabajadorRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;
import cl.safe.entity.ConsultaMedicaFullTrabajadorEntity;
import cl.safe.entity.EmpresaEntity;
import cl.safe.entity.ExamenEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.CertificadoService;
import cl.safe.service.EmpresaService;
import cl.safe.service.ExamenService;
import io.jsonwebtoken.Claims;

@Controller
@RequestMapping("/descargas/certificados")
public class CertificadoDowloadController {
	
	@Autowired
	CertificadoService certificadoService;
	
	@Autowired
	private ExamenService examenService;

	@PostMapping("/capacitacion")
	public String ver(
			@RequestBody @Valid final CapacitacionParaTrabajadorRequestDto cr,
			Model model) {
		
		CapacitacionParaTrabajadorEntity c = certificadoService.getCetificado(cr.getRun(), cr.getEmpresaId(), cr.getAsistenciaId());
		
		System.out.println(c);
		
		model.addAttribute("certificado", c);
		return "certificado/ver";
	}
	
	@PostMapping("/consulta-medica")
	public String ver(
			@RequestBody @Valid final ConsultaMedicaParaTrabajadorRequestDto cr,
			Model model) {
		
		ConsultaMedicaFullTrabajadorEntity c = certificadoService.getConsultas(cr.getRun(), cr.getEmpresaId(), cr.getConsultaId());

		List<ExamenEntity> examenes = examenService.getAllExamenesByConsultaId(cr.getConsultaId());

		model.addAttribute("consulta", c);
		model.addAttribute("examenes", examenes);
		
		return "consulta/ver";
	}
}
