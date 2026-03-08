package foro.hub.foro.controller;

import foro.hub.foro.domain.Usuario;
import foro.hub.foro.dto.DatosAutenticacion;
import foro.hub.foro.dto.DatosTokenJWT;
import foro.hub.foro.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DatosAutenticacion datos) {

        System.out.println("LOGIN EJECUTADO");

        var authToken = new UsernamePasswordAuthenticationToken(
                datos.login(),
                datos.clave()
        );
        var autenticacion = authenticationManager.authenticate(authToken);
        var token = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(token));
    }
}
