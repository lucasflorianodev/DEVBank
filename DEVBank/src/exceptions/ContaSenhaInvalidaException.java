package exceptions;

public class ContaSenhaInvalidaException extends IllegalArgumentException{

    public ContaSenhaInvalidaException() {
        super("Conta e Senha inválida");
    }

    public ContaSenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}