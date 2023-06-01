package Origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

import java.text.NumberFormat;
import java.util.Locale;

public class ClienteContaDigital {

    private final String numeroConta;
    private final String senha;
    private final String apelido = null;
    private final String tipoCartao = null;
    private int limiteCredito = 0;
    private double saldoContaCorrente = 0;
    private double saldoPoupanca = 0;
    private double saldoInvestimentos = 0;
    private double saldoDevEconomy = 0;

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

        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldoContaCorrente = saldoContaCorrente;
        this.saldoPoupanca = saldoPoupanca;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public String getApelido() {
        return apelido;
    }

    public String getTipoCartao() {
        return tipoCartao;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public double getSaldoContaCorrente() {
        return saldoContaCorrente;
    }

    public double getSaldoPoupanca() {
        return saldoPoupanca;
    }

    public double getSaldoInvestimentos() {
        return saldoInvestimentos;
    }

    public double getSaldoDevEconomy() {
        return saldoDevEconomy;
    }

    public String getSaldoContaCorrenteFormatado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(saldoContaCorrente);
    }

    public void setSenha(String senha) {
    }

    public void toString(String senha) {
    }
}
