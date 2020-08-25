package io.github.uriasgadelha.clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.uriasgadelha.clientes.exception.UsuarioCadastradoException;
import io.github.uriasgadelha.clientes.model.entity.Usuario;
import io.github.uriasgadelha.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;

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
    
    @GetMapping("/listarUsuarios")
    public List<String> listarNomeUsuario() {    	   
           return servicoUsuario.listarNomeUsuarios();       
    }
    
    @GetMapping("/listarRolesUsuario")
    public List<String> listarRoleUsuario(
    		@RequestParam(value = "nomeUsuario", required = true) String nomeUsuario) {    	   
           return servicoUsuario.listarRolesUsuario(nomeUsuario);       
    }
    
    @PatchMapping("/alteraRolesUsuario")    
    public void alteraRoles(@RequestParam(value = "nomeUsuario", required = true) String nomeUsuario,
    		                @RequestParam(value = "rolesUsuario", required = true) String rolesUsuario) {
       servicoUsuario.alteraRolesUsuario(nomeUsuario, rolesUsuario);
    }
    
}
