package cl.safe.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.dto.LoginDto;
import cl.safe.dto.LoginRequest;
import cl.safe.dto.ResponseDto;
import cl.safe.entity.UserEntity;
import cl.safe.service.UserService;

@RestController
public class LoginController {

  @Value("${jwt.secret}")
  private String secret;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDto<LoginDto>> login(@RequestBody @Valid final LoginRequest login) throws ServletException {
    final boolean existUser = userService.existUser(login.getEmail(), login.getPassword());
    if (!existUser) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    final Instant now = Instant.now();

    final String jwt = Jwts.builder()
        .setSubject(login.getEmail())
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
        .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
        .compact();
    
    
    ResponseDto<LoginDto> responseDto = new ResponseDto<>();
	LoginDto loginDto = new LoginDto();
	loginDto.setToken(jwt);
	responseDto.setObj(loginDto);
	responseDto.setMessage("SUCCESS");
	responseDto.setStatus(HttpStatus.OK);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

}
