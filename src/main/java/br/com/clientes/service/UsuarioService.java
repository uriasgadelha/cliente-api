package br.com.clientes.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.clientes.exception.UsuarioCadastradoException;
import br.com.clientes.model.entity.Usuario;
import br.com.clientes.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {

        boolean exists = usuarioRepository.existsByUsername(usuario.getUsername());

        if (exists) {
            throw new UsuarioCadastradoException(usuario.getUsername());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));       
        
        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().split(","))
                .build()
                ;
    }

	public List<String> listarNomeUsuarios() {
		return usuarioRepository.listarUsuarios();		
	}
	
	public List<String> listarRolesUsuario(String username) {
		Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));		
		
		return Arrays.asList(usuario.getRoles().split(","));				
	}
	
	public void alteraRolesUsuario(String username, String roles) {
		Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		usuario.setRoles(roles);
		
		usuarioRepository.save(usuario);
	}
	
}
