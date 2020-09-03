package br.com.clientes.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
    
    @Query(" select username " +
            "from Usuario " +            
            "order by username ")
    List<String> listarUsuarios();
    
    List<Usuario> findAllByOrderByIdAsc();
}
