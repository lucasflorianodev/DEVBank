package Origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

public class ClienteContaDigital {

    private final double saldoContaCorrente = 0;
    private final double saldoContaPoupanca = 0;
    private final double saldoInvestimentos = 0;
    private final double saldoDevEconomy = 0;

    public ClienteContaDigital(String numeroConta, String senha) throws ContaSenhaInvalidaException {
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

    }

    public double getSaldoContaCorrente() {
        return saldoContaCorrente;
    }

    public double getSaldoPoupanca() {
        return saldoContaPoupanca;
    }

    public double getSaldoInvestimentos() {
        return saldoInvestimentos;
    }

    public double getSaldoDevEconomy() {
        return saldoDevEconomy;
    }
}