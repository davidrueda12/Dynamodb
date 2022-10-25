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

    public Usuario toPatchUsuario(UsuarioDTO usuarioDTO, Usuario usuario){

        if ((usuarioDTO.getPassword() != null)) {
            usuario.setPassword(usuarioDTO.getPassword());
        }
        if ((usuarioDTO.getNombre() != null)) {
            usuario.setNombre(usuarioDTO.getNombre());
        }
        if ((usuarioDTO.getApellido() != null)) {
            usuario.setApellido(usuarioDTO.getApellido());
        }
        if ((usuarioDTO.getEmail() != null)) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if ((usuarioDTO.getTelefono() != null)) {
            usuario.setTelefono(usuarioDTO.getTelefono());
        }
        return usuario;
    }
}
