package cl.safe.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.safe.dto.RegisterRequest;
import cl.safe.dto.UserJson;
import cl.safe.service.UserService;

@RestController
@RequestMapping("/register")
public class RegistrerController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.POST)
  public UserJson registerUser(@RequestBody @Valid final RegisterRequest registerRequest) throws NotFoundException {
    return userService.registerUser(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getName(), registerRequest.getSurname());
  }

}
