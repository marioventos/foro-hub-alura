package foro.hub.foro.service;

import foro.hub.foro.domain.Usuario;
import foro.hub.foro.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public void registrarAdmin(){
        String claveHasheada = encoder.encode("76261421");
        Usuario admin = new Usuario("admin", claveHasheada);
        usuarioRepository.save(admin);
    }
}
