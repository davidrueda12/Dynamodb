package com.service.nombre.login.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {

    @NotNull(message = "El campo username no ser null")
    private String username;
    @NotNull(message = "El campo password no ser null")
    private String password;
    private String nombre;
    private String apellido;
    @NotNull(message = "El campo email no ser null")
    private String email;
    @NotNull(message = "El campo telefono no ser null")
    private String telefono;
}
