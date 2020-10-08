package br.com.fiap.coolshoes.controller;

import br.com.fiap.coolshoes.dto.AuthDTO;
import br.com.fiap.coolshoes.dto.TokenDTO;
import br.com.fiap.coolshoes.dto.UserCreateDTO;
import br.com.fiap.coolshoes.dto.UserDTO;
import br.com.fiap.coolshoes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserCreateDTO userCreateDTO){
        return userService.create(userCreateDTO);
    }

    @PostMapping("login")
    public TokenDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }

}
