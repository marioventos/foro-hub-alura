package foro.hub.foro.service;

import foro.hub.foro.domain.Usuario;
import foro.hub.foro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario login(String login, String clave) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if(usuario != null && usuario.getPassword().equals(clave)){
            return usuario;
        }
        return null;
    }
}
