package br.com.clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.clientes.exception.UsuarioCadastradoException;
import br.com.clientes.model.entity.Usuario;
import br.com.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService servicoUsuario;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
       try{
          return servicoUsuario.salvar(usuario);
       } catch (UsuarioCadastradoException e) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   e.getMessage());
       }
    }
    
    @GetMapping("/listarNomesUsuarios")
    public List<String> listarNomeUsuario() {    	   
           return servicoUsuario.listarNomeUsuarios();       
    }
    
    @GetMapping
    public List<Usuario> obterTodos() {
        return servicoUsuario.obterTodos();
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
    
    @GetMapping("{id}")
    public Usuario acharPorId(@PathVariable Integer id) {
        return servicoUsuario.getPorId(id)
        		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
    	servicoUsuario.getPorId(id)
                .map( usuario -> {
                	servicoUsuario.deletar(usuario);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Usuario usuarioAtualizado ) {

    	servicoUsuario.getPorId(id)
                .map( usuario -> {
                	usuarioAtualizado.setId(usuario.getId());
                    return servicoUsuario.atualizar(usuarioAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
    
}
