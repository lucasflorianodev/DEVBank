package Investimentos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvestimentoHistorico {
    private final List<Investimento> investimentos;

    public InvestimentoHistorico() {
        this.investimentos = new ArrayList<>();
    }

    public void adicionarInvestimento(Investimento investimento) {
        investimentos.add(investimento);
    }

    public void mostrarHistorico() {
        for (Investimento investimento : investimentos) {
            System.out.println("Nome do investimento: " + investimento.getNome());
            System.out.println("Valor do investimento: " + investimento.getValor());
            System.out.println("Data do investimento: " + investimento.getData());
            System.out.println();
        }
    }
    public static void main(String[] args) {
        InvestimentoHistorico historico = new InvestimentoHistorico();

        Investimento investimento1 = new Investimento("HGRU11", 1300, new Date());
        Investimento investimento2 = new Investimento("TRXF11", 800, new Date());

        historico.adicionarInvestimento(investimento1);
        historico.adicionarInvestimento(investimento2);

        historico.mostrarHistorico();
    }
}
