package com.duoc.veterinaria.app.Controller;

import com.duoc.veterinaria.app.seguridad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // RECIBE { "username": "...", "password": "..." } EN JSON Y DEVUELVE EL TOKEN
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credenciales) {

        String username = credenciales.get("username");
        String password = credenciales.get("password");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtUtil.generateToken(userDetails);

        return Map.of("token", token);
    }
}