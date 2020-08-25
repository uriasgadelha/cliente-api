package br.com.clientes.exception;

public class UsuarioCadastradoException extends RuntimeException{

    public UsuarioCadastradoException(String login) {
        super("Usuário já cadastrado. "+login);
    }

}
