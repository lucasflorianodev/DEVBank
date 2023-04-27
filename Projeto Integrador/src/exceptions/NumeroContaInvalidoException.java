package exceptions;

public class NumeroContaInvalidoException extends IllegalArgumentException {

    public NumeroContaInvalidoException() {
        super("Numero de Conta inválida");
    }

    public NumeroContaInvalidoException(String mensagem) {
        super(mensagem);
    }
}
