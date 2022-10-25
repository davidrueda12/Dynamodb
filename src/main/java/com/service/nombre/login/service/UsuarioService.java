package com.service.nombre.login.service;

import com.service.nombre.login.models.UsuarioDTO;
import com.service.nombre.login.models.UsuarioLoginDTO;
import com.service.nombre.login.models.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsuarioService {

    ResponseEntity<?> byUsername(UsuarioLoginDTO username);
    ResponseEntity<?> saveUser(UsuarioDTO usuarioDTO);
    ResponseEntity<Usuario> patchUser(UsuarioDTO usuarioDTO);
    ResponseEntity<String> deleteUser(UsuarioLoginDTO usuarioDTO);

}
