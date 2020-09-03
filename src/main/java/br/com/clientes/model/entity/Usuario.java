package br.com.clientes.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;

    @Column()
    private String roles;
    
    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    
    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
