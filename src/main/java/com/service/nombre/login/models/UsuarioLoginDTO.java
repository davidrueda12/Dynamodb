package com.service.nombre.login.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioLoginDTO {

    @NotNull(message = "El campo username no ser null")
    private String username;
    @NotNull(message = "El campo password no ser null")
    private String password;
}
