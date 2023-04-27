package origem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ContaPoupanca {
    static double saldo;
    private static ArrayList<String> extrato = new ArrayList<>();
    private static Scanner scanner;

    public ContaPoupanca(String contaPoupanca, Scanner scanner, double saldoInicial) {
        ContaPoupanca.scanner = scanner;
        saldo = saldoInicial;
        extrato = new ArrayList<>();
    }

    public ContaPoupanca(double saldopoupanca, ContaCorrente contaCorrente) {
    }

    public static void exibirOpcoesContaPoupanca() {
        boolean sair = false;

        while (!sair) {
            System.out.println("===============================");
            System.out.println(" POUPANÇA DEVBANK");
            System.out.println("===============================");
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Rendimentos");
            System.out.println("3 - Consultar Extrato Mensal");
            System.out.println("4 - Transferir para Corrente");
            System.out.println("0 - Encerrar operação e volta para tela inicial");
            System.out.print("Opção: ");

            if (scanner == null) { // verifica se o scanner não foi inicializado
                scanner = new Scanner(System.in);
            }

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    sair = true;
                    break;
                case 1:
                    System.out.printf("Saldo atual: R$%.2f\n", saldo);
                    break;
                case 2:
                    double rendimento = calcularRendimento();
                    System.out.printf("Rendimento atual: R$%.2f\n", rendimento);
                    break;
                case 3:
                    exibirExtrato();
                    break;
                case 4:
                    transferirParaContaCorrente();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static double calcularRendimento() {
        double rendimento = saldo * 0.005;
        saldo += rendimento;
        extrato.add(String.format("Rendimento de R$%.2f em %s", rendimento, obterDataAtual()));
        return rendimento;
    }

    private static void exibirExtrato() {
        if (extrato.isEmpty()) {
            System.out.println("Não há movimentações no extrato.");
            return;
        }

        System.out.println("Extrato:");
        for (String movimentacao : extrato) {
            System.out.println("- " + movimentacao);
        }
    }

    private static void transferirParaContaCorrente() {
        System.out.print("Digite o valor que deseja transferir para a conta corrente: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (saldo < valor) {
            System.out.println("Saldo insuficiente para efetuar a transferência.");
            return;
        }

        saldo -= valor;
        extrato.add(String.format("Transferência de R$%.2f para a conta corrente em %s", valor, obterDataAtual()));
        ContaCorrente.depositar(valor);
        System.out.println("Transferência efetuada com sucesso!");

        System.out.print("Deseja efetuar outra Transferência? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra Transferência? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            transferirParaContaCorrente();
        }
    }

    private static String obterDataAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataAtual = new Date();
        return formatter.format(dataAtual);
    }

    public void creditar() {
    }
}