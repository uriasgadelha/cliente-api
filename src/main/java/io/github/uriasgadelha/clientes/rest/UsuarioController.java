package io.github.uriasgadelha.clientes.rest;

import io.github.uriasgadelha.clientes.exception.UsuarioCadastradoException;
import io.github.uriasgadelha.clientes.model.entity.Usuario;
import io.github.uriasgadelha.clientes.model.repository.UsuarioRepository;
import io.github.uriasgadelha.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService servicoUsuario;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) {
       try{
           servicoUsuario.salvar(usuario);
       } catch (UsuarioCadastradoException e) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   e.getMessage());
       }
    }
}
