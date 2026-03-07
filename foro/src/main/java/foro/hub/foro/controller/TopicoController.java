package foro.hub.foro.controller;

import foro.hub.foro.domain.Topico;
import foro.hub.foro.exception.RecursoNoEncontradoException;
import foro.hub.foro.repository.TopicoRepository;
import foro.hub.foro.dto.DatosListadoTopico;
import foro.hub.foro.dto.DatosRegistroTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    public TopicoRepository repository;

    //LISTAR TODOS
    @GetMapping
    public Page<DatosListadoTopico> listar(Pageable paginacion) {
        return repository.findAll(
                PageRequest.of(
                        paginacion.getPageNumber(),
                        paginacion.getPageSize(),
                        Sort.by("fechaCreacion").descending()
                )
        ).map(DatosListadoTopico::new);

    }
    //REGISTRAR
    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){

        if(repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = new Topico();

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(true);

        repository.save(topico);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DatosListadoTopico(topico));
    }
    //LISTAR POR ID
    @GetMapping("/{id}")
    public DatosListadoTopico listarTopicos(@PathVariable Long id){
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tópico no encontrado con id: " + id));

        return new DatosListadoTopico(topico);
    }//ACTUALIZAR
    @PutMapping("/{id}")
    @Transactional
    public DatosListadoTopico actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosListadoTopico datosActualizados){
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tópico no encontrado con id: " + id));

        topico.setTitulo(datosActualizados.titulo());
        topico.setMensaje(datosActualizados.mensaje());
        topico.setAutor(datosActualizados.autor());
        topico.setCurso(datosActualizados.curso());

        repository.save(topico);

        return new DatosListadoTopico(topico);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(("Tópico no encontrado con id: " + id)));
        repository.delete(topico);
    }
    @PatchMapping("/{id}/cerrar")
    @Transactional
    public ResponseEntity cerrarTopico(@PathVariable Long id){

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Tópico no encontrado con id: " + id));
        topico.setStatus(false);

        return ResponseEntity.ok().build();
    }

}
