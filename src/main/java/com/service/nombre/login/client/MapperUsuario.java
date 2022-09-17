package com.service.nombre.login.client;

import com.service.nombre.login.models.UsuarioDTO;
import com.service.nombre.login.models.entity.Usuario;

public class MapperUsuario {

    public Usuario toUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());

        return usuario;
    }
}
