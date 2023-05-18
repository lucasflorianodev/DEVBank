package exceptions;

public class SenhaInvalidaException extends IllegalArgumentException {

    public SenhaInvalidaException() {
        super("Senha inválida");
    }

    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
