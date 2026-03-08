package foro.hub.foro.security;

import foro.hub.foro.domain.Usuario;
import foro.hub.foro.repository.UsuarioRepository;
import foro.hub.foro.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.tool.schema.extract.spi.ExtractionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = recuperarToken(request);

        if(token != null){
            try{
                String login = tokenService.getSubject(token);
                Usuario usuario = usuarioRepository.findByLogin(login);

                if(usuario != null){
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    usuario, null, usuario.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch(Exception e){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Token inválido");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }
    private String recuperarToken(HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null &&  authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
