package Investimentos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Investimento {
    private final String nome;
    private final double valor;
    private final Date data;

    public Investimento(String nome, double valor, Date data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }
}

