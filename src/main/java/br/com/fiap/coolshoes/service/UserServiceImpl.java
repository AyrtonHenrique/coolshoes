package br.com.fiap.coolshoes.service;

import br.com.fiap.coolshoes.dto.AuthDTO;
import br.com.fiap.coolshoes.dto.TokenDTO;
import br.com.fiap.coolshoes.dto.UserCreateDTO;
import br.com.fiap.coolshoes.dto.UserDTO;
import br.com.fiap.coolshoes.entity.User;
import br.com.fiap.coolshoes.repository.UserRepository;
import br.com.fiap.coolshoes.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        User savedUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());

        return userDTO;
    }

    @Override
    public TokenDTO login(AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        } catch (DisabledException disabledException){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuario.desabilitado");
        } catch (BadCredentialsException badCredentialsException){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "credenciais.invalidas");
        }
        String token = jwtTokenUtil.generateToken(authDTO.getUsername());

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        return tokenDTO;
    }
}
