package cl.safe.user.controller;

import io.jsonwebtoken.Claims;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.controller.NotFoundException;
import cl.safe.entity.UserEntity;
import cl.safe.service.UserServiceSP;
import cl.safe.user.UserService;
import cl.safe.user.controller.response.UserJson;

@RestController
@RequestMapping("/api/me")
public class MeController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserServiceSP userServiceSP;
  
  
  @GetMapping("/listado")
	public List<UserEntity> findAll() {
		return userServiceSP.getAllUsers();
	}
  
  @RequestMapping(method = RequestMethod.GET)
  public UserJson getUser(@RequestAttribute("claims") final Claims claims) throws NotFoundException {
    return userService.getUserByEmail(claims.getSubject());
  }

  
}
