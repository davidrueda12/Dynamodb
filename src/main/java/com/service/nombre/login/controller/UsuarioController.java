package com.service.nombre.login.controller;

import com.service.nombre.login.models.UsuarioDTO;
import com.service.nombre.login.models.UsuarioLoginDTO;
import com.service.nombre.login.models.entity.Usuario;
import com.service.nombre.login.service.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class UsuarioController {

    private UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService){
        this.usuarioService=usuarioService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/conectar",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> encontrar(@Valid @RequestBody UsuarioLoginDTO usuarioDTO){
        log.info("se esta ejecutando un login");
        return usuarioService.byUsername(usuarioDTO);
    }

    @PostMapping(value = "/registrar",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> guardar(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.saveUser(usuarioDTO);
    }

    @PatchMapping(value = "/modificar",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> modificar(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.patchUser(usuarioDTO);
    }

    @DeleteMapping(value = "/eliminar",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modificar(@Valid @RequestBody UsuarioLoginDTO usuarioDTO){
        return usuarioService.deleteUser(usuarioDTO);
    }
}
