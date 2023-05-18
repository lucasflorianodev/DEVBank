package origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

import java.text.NumberFormat;
import java.util.Locale;

public class Cliente {

    private final double saldo;
    private final double saldopoupanca;

    public Cliente(String numeroConta, String senha) throws ContaSenhaInvalidaException {
        // verifica se tanto o número da conta quanto a senha são válidos
        if (!numeroConta.matches("\\d{6}-\\d{3}") && !senha.matches("\\d{6}")) {
            throw new ContaSenhaInvalidaException("Número da Conta e Senha inválida");
        }
        // verifica se o número da conta é válido
        if (!numeroConta.matches("\\d{6}-\\d{3}")) {
            throw new NumeroContaInvalidoException("Número da conta inválido");
        }

        // verifica se a senha é válida
        if (!senha.matches("\\d{6}")) {
            throw new SenhaInvalidaException("Senha inválida");
        }

        this.saldo = 23500.00; // saldo em conta definido como 23.500,00
        this.saldopoupanca = 10500.00; // saldo em conta definido como 10.500,00
    }

    public double getSaldo() {
        return saldo;
    }

    public double getSaldopoupanca() { return saldo;}

    public String getNome() {
        // Aqui você poderia consultar o banco de dados para obter o nome
        return "Fulano de Tal";
    }

    public String getSaldoFormatado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(saldo);
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
    }
}