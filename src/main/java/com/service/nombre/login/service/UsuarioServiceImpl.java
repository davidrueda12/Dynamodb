package com.service.nombre.login.service;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.service.nombre.login.client.MapperUsuario;
import com.service.nombre.login.models.UsuarioDTO;
import com.service.nombre.login.models.UsuarioLoginDTO;
import com.service.nombre.login.models.entity.Usuario;
import com.service.nombre.login.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;
    MapperUsuario mapperUsuario = new MapperUsuario();

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> byUsername(UsuarioLoginDTO username) {

        Optional<Usuario> user = usuarioRepository.findByUsername(username.getUsername());

        if (user.isPresent()){
            if (!user.get().getPassword().equals(username.getPassword())){
                log.info("contraseña incorrecta");
                return new ResponseEntity<>("CONTRASEÑA INCORRECTA",HttpStatus.FORBIDDEN);
            }
            log.info("usuario "+username.getUsername()+" encontrado");
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }
        log.info("no se encontro el usuario "+username.getUsername());
        return new ResponseEntity<>("NO SE ENCONTRO EL USUARIO",HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveUser(UsuarioDTO usuarioDTO) {
        log.info("Guardando el usuario: "+usuarioDTO.getUsername());

        Optional<Usuario> user = usuarioRepository.findByUsername(usuarioDTO.getUsername());
        Optional<Usuario> userEmail = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        Optional<Usuario> userTelefono = usuarioRepository.findByTelefono(usuarioDTO.getTelefono());

        if (user.isPresent()){
            log.info("El usuario "+usuarioDTO.getUsername()+" ya esta registrado");
            return new ResponseEntity<>("El usuario "+usuarioDTO.getUsername()+" ya esta registrado",HttpStatus.OK);
        }
        if(userEmail.isPresent()){
            log.info("El email "+usuarioDTO.getEmail()+" ya esta registrado");
            return new ResponseEntity<>("El email "+usuarioDTO.getEmail()+" ya esta registrado",HttpStatus.OK);
        }
        if(userTelefono.isPresent()){
            log.info("El telefono "+usuarioDTO.getTelefono()+" ya esta registrado");
            return new ResponseEntity<>("El telefono "+usuarioDTO.getTelefono()+" ya esta registrado",HttpStatus.OK);
        }

        Usuario usuario = mapperUsuario.toUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario,HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Usuario> patchUser(UsuarioDTO usuarioDTO) {
        log.info("Modificando el usuario: "+usuarioDTO.getUsername());
        Usuario user = usuarioRepository.findByUsername(usuarioDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro le usuario: "+ usuarioDTO.getUsername()));
        Usuario userPatch = mapperUsuario.toPatchUsuario(usuarioDTO, user);
        usuarioRepository.save(userPatch);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteUser(UsuarioLoginDTO usuarioDTO) {
        log.info("Eliminando el usuario: "+usuarioDTO.getUsername());
        Usuario user = usuarioRepository.findByUsername(usuarioDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro le usuario: "+ usuarioDTO.getUsername()));
        if(!usuarioDTO.getPassword().equals(user.getPassword())){
            log.info("contraseña incorrecta");
            return new ResponseEntity<>("CONTRASEÑA INCORRECTA",HttpStatus.FORBIDDEN);
        }
        usuarioRepository.delete(user);
        return new ResponseEntity<>("Se a eliminado el usuario: "+user.getUsername(),HttpStatus.OK);
    }
}
