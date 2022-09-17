package com.service.nombre.login.service;

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
    public ResponseEntity<Usuario> saveUser(UsuarioDTO usuarioDTO) {
        MapperUsuario mapperUsuario = new MapperUsuario();
        Usuario usuario = mapperUsuario.toUsuario(usuarioDTO);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario,HttpStatus.CREATED);
    }
}
