package cl.safe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import cl.safe.dto.AsistenciaDtoResponse;
import cl.safe.dto.AsistenciaRequestDto;
import cl.safe.dto.CapacitacionRequestDto;
import cl.safe.dto.CapacitacioneByEmpresaIdRequestDto;
import cl.safe.dto.InformeInstalacionDtoRequest;
import cl.safe.dto.PaginacionObjetoResponseDto;
import cl.safe.dto.PaginacionRequestDto;
import cl.safe.dto.ResponseDto;
import cl.safe.dto.VisitaMedicaRequestDto;
import cl.safe.entity.AsistenciaTrabajadorEntity;
import cl.safe.entity.CapacitacionEntity;
import cl.safe.entity.UserEntity;
import cl.safe.entity.VisitaMedicaEntity;
import cl.safe.service.CapacitacionService;
import cl.safe.service.FileStorageService;
import cl.safe.service.UserService;
import cl.safe.service.UserServiceSP;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api/capacitaciones")
public class CapacitacionController {

	private static final Logger logger = LoggerFactory.getLogger(AsisteniciasController.class);

	@Autowired
	CapacitacionService capacitacionService;
	
	@Autowired
	private UserServiceSP userServiceSP;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	HttpServletRequest request;
	
	@GetMapping("/examinador/{empresaId}")
	public ResponseEntity<ResponseDto<List<CapacitacionEntity>>> findAllByEmpresaExaminador(@RequestAttribute("claims") final Claims claims, @PathVariable(name="empresaId") Long empresaId) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.EXAMINADOR)) {
			ResponseDto<List<CapacitacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.findAllByEmpresaExaminadorSP(empresaId, u.getId()));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/supervisor/{empresaId}")
	public ResponseEntity<ResponseDto<List<CapacitacionEntity>>> findAllByEmpresaSupervisorExaminador(@RequestAttribute("claims") final Claims claims, @PathVariable(name="empresaId") Long empresaId) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.SUPERVISOR)) {
			ResponseDto<List<CapacitacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.findAllByEmpresaSupervisorSP(empresaId, u.getId()));
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
	public ResponseEntity<ResponseDto<List<AsistenciaDtoResponse>>> asistenciasByCapacitacionId(@RequestAttribute("claims") final Claims claims, @PathVariable(name="id") Long id) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.ADMIN_SAFE, Const.EXAMINADOR, Const.SUPERVISOR)) {
			ResponseDto<List<AsistenciaDtoResponse>> rdto = new ResponseDto<>();
			
			
			List<AsistenciaTrabajadorEntity> asistencias = capacitacionService.findAllAsistentesByCapacitacionId(id);
			
			List<AsistenciaDtoResponse> asistenciasResponse = new ArrayList<>();
			
			asistencias.forEach(new Consumer<AsistenciaTrabajadorEntity>() {

				@Override
				public void accept(AsistenciaTrabajadorEntity t) {
					
					AsistenciaDtoResponse asistenciaResponse = new AsistenciaDtoResponse();
					asistenciaResponse.setApellidoMaterno(t.getApellidoMaterno());
					asistenciaResponse.setApellidoPaterno(t.getApellidoPaterno());
					asistenciaResponse.setAsistenciaId(t.getAsistenciaId());
					asistenciaResponse.setCapacitacionId(t.getCapacitacionId());
					asistenciaResponse.setEmail(t.getEmail());
					asistenciaResponse.setFirmaOriginal(t.getFirma());
					asistenciaResponse.setNombre(t.getNombre());
					asistenciaResponse.setRun(t.getRun());
					asistenciaResponse.setTrabajadorId(t.getTrabajadorId());
					asistenciasResponse.add(asistenciaResponse);
					
					if (t.getFirma() != null) {
						try {
							asistenciaResponse.setFirma(fileNameToBase64(t.getFirma()));
						} catch (IOException e) {
							t.setFirma("false");
						}
					}
				}
				
			});
			rdto.setObj(asistenciasResponse);
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseDto<Long>> crearCapacitacion(
			@RequestAttribute("claims") final Claims claims,
			@RequestBody @Valid final CapacitacionRequestDto capacitacionEntity) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());
		
		System.out.println(capacitacionEntity);
		
		if (Utils.hasProfile(u, Const.SUPERVISOR)) {
			
			UserEntity examinador = userService.findOne(capacitacionEntity.getExaminador());
			
			if (!Utils.hasProfile(examinador, Const.EXAMINADOR)) {
				return Utils.responseUnauthorized("El usuario examinador no posee el perfil de examinador");
			}
			
			capacitacionEntity.setSupervisor(u.getId());
			
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.crearCapacitacion(capacitacionEntity));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@GetMapping("/cerrar-capacitacion/{capacitacionId}")
	public ResponseEntity<ResponseDto<Long>> cerrarCapacitacion(
			@RequestAttribute("claims") final Claims claims,
			@PathVariable(name="capacitacionId") Long capacitacionId) {
		UserEntity u = userServiceSP.findByEmail(claims.getSubject());

		if (Utils.hasProfile(u, Const.EXAMINADOR)) {
			ResponseDto<Long> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.cerrarCapacitacion(capacitacionId));
			return new ResponseEntity<>(rdto, HttpStatus.OK);
		}
			
		return Utils.responseUnauthorized();
	}
	
	@PostMapping("/admin-empresa")
	public ResponseEntity<ResponseDto<PaginacionObjetoResponseDto<CapacitacionEntity>>> getAllInformeInstalacionADMINEMPRESA_PAG(
			@RequestAttribute("claims") final Claims claims,
			final @RequestBody PaginacionRequestDto instaParam) {
		UserEntity user = userServiceSP.findByEmail(claims.getSubject());
		
		if (user.getEmpresaFk() == null) {
			return Utils.responseUnauthorized("No registra empresa asignada como Administrador");
		}

		if (Utils.hasProfile(user, Const.ADMIN_EMPRESA)) {
			ResponseDto<PaginacionObjetoResponseDto<CapacitacionEntity>> rdto = new ResponseDto<>();
			rdto.setObj(capacitacionService.getCapacitacionesADMINEMPRESA_PAG(
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
	
	
	private String fileNameToBase64(String fileName) throws IOException {
		 // /{fileName:.+}@PathVariable String fileName,
		 // Load file as Resour ce
		 
		 	System.out.println(fileName);
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        byte[] imageBytes = loadFile(resource.getFile());
	        String imageStr = Base64.encodeBase64String(imageBytes);
	        
	        Path path = new File(resource.getFilename()).toPath();
	        String mimeType = Files.probeContentType(path);

	        String base64 = "data:" + mimeType + ";base64," + imageStr;
	        
	        System.out.println(base64);
	        
	        return base64;
	        
	        /*
	        System.out.println(contentType);
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType("image/jpg"))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	        */
	    }
	 
	 
	 	private static byte[] loadFile(File file) throws IOException {
		    InputStream is = new FileInputStream(file);

		    long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    }
		    byte[] bytes = new byte[(int)length];
		    
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length
		           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }

		    if (offset < bytes.length) {
		        throw new IOException("Could not completely read file "+file.getName());
		    }

		    is.close();
		    return bytes;
		}
}
