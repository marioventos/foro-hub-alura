package foro.hub.foro.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(

    @NotBlank
    String login,

    @NotBlank
    String clave
    )
{}
