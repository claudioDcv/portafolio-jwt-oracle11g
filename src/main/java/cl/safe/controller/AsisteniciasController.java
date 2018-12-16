package cl.safe.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.safe.config.Const;
import cl.safe.config.Utils;
import cl.safe.dto.AsistenciaRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.UploadFileResponse;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.service.AsistenciaService;
import cl.safe.service.CapacitacionService;
import cl.safe.service.FileStorageService;
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
	
	@Autowired
	FileStorageService fileStorageService;
	

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

	@PostMapping("/uploadFile")
    public String uploadFile(
    		@RequestAttribute("claims") final Claims claims,
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("id") String id,
    		@RequestParam("firmaOriginal") String firmaOriginal
    ) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		System.out.println("------------------------------------------");
		System.out.println(file.getOriginalFilename());
		System.out.println("------------------------------------------");

		if (Utils.hasProfile(u, Const.EXAMINADOR)) {
			String newName = "userId-" + id + "-" + file.getOriginalFilename();
			String fileName = fileStorageService.storeFile(file, newName);
			
			if (firmaOriginal != null) {
				fileStorageService.delete(firmaOriginal);
			}
			
	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/certificados/downloadFile/")
	                .path(fileName)
	                .toUriString();

	        UploadFileResponse uf = new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	        return fileDownloadUri;
		}
		return "No Autorizado";
		
	}
	
	
	private BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    
}
