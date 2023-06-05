package Origem;

import static Origem.ContaCorrente.saldocorrente;
import static Origem.ContaPoupanca.saldopoupanca;
import static Origem.Investimentos.saldoInvestimentos;
import static Origem.DevEconomy.saldoDevEconomy;


public class InformesRendimento {
    public static void exibirOpcoesInformeRendimentos() {
        System.out.println("\n===============================");
        System.out.println("INFORME DE RENDIMENTOS");
        System.out.println("===============================");
        System.out.println("Bem-vindo ao sistema de solicitação de informe de rendimentos!");
        System.out.print("Digite o seu e-mail: ");

        System.out.println("\nProcessando os Dados no Sistema");
        System.out.println("||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||");

        boolean informeDisponivel = verificarDisponibilidadeInforme();

        if (informeDisponivel) {
            System.out.println("Informe de rendimentos enviado para o seu e-mail.");
        } else {
            System.out.println("Desculpe, o informe de rendimentos não está disponível no momento.");
        }
    }

    private static boolean verificarDisponibilidadeInforme() {
        double saldoContaCorrente = obterSaldoContaCorrente();
        double saldoContaPoupanca = obterSaldoContaPoupanca();
        double saldoInvestimentos = obterSaldoInvestimentos();
        double saldoDevEconomy = obterSaldoDevEconomy();
        double limiteMinimoSaldo = 28559.70;

        return saldoContaCorrente + saldoContaPoupanca + saldoInvestimentos + saldoDevEconomy >= limiteMinimoSaldo;
    }

    private static double obterSaldoContaCorrente() {
        return saldocorrente;
    }

    private static double obterSaldoContaPoupanca() {
        return saldopoupanca;
    }

    private static double obterSaldoInvestimentos() {
        return saldoInvestimentos;
    }

    private static double obterSaldoDevEconomy() {
        return saldoDevEconomy;
    }
}
