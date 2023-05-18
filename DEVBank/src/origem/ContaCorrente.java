package origem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ContaCorrente {
    private static double saldo;
    private static List<String> extrato;
    private static ContaPoupanca contaPoupanca;
    private static Scanner scanner = new Scanner(System.in);


    public ContaCorrente(double saldoInicial, ContaPoupanca contaPoupanca) {
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        this.contaPoupanca = contaPoupanca;
        this.scanner = new Scanner(System.in);
    }

    public static void exibirOpcoesContaCorrente() {
        boolean sair = false;
        while (!sair) {
            System.out.println("===============================");
            System.out.println(" CORRENTE DEVBANK");
            System.out.println("===============================");
            System.out.println("\nOpções de Serviço");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Extrato Mensal");
            System.out.println("3 - Transferir para Poupança");
            System.out.println("0 - Encerrar operação e volta para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    extrato();
                    break;
                case 3:
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); // Consome a quebra de linha deixada pelo nextDouble()
                    transferirParaContaPoupanca();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente");
                    break;
            }
        }
    }


    public static void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldo);
    }

    public static void extrato() {
        // Gerar operações aleatórias no extrato dos últimos 30 dias
        Random random = new Random();
        int numOperacoes = random.nextInt(6) + 1; // Entre 1 e 6 operações aleatórias
        for (int i = 29; i >= 0; i--) {
            int opcao = random.nextInt(4) + 1; // Escolher uma operação aleatória

            LocalDate dataOperacao = LocalDate.now().minusDays(i); // Data aleatória dentro do período do extrato mensal

            switch (opcao) {
                case 1:
                    double valor = random.nextDouble() * 1000; // Valor aleatório até R$ 1000
                    debitar(valor);
                    System.out.printf("%s - Débito: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
                case 2:
                    valor = random.nextDouble() * 10000; // Valor aleatório até R$ 10.000
                    contaPoupanca.creditar();
                    System.out.printf("%s - Crédito: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
                case 3:
                    valor = random.nextDouble() * 5000; // Valor aleatório até R$ 5.000
                    contaPoupanca.creditar();
                    System.out.printf("%s - Transferência para Conta Poupança: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
                case 4:
                    valor = random.nextDouble() * 5000; // Valor aleatório até R$ 5.000
                    Transferencia transferencia = new Transferencia(valor);
                    transferencia.realizarTransferencia();
                    System.out.printf("%s - Transferência: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
                case 5:
                    valor = random.nextDouble() * 10000; // Valor aleatório até R$ 10.000
                    Pagamento pagamento = new Pagamento();
                    Pagamento.realizarPagamento();
                    new Pagamento();
                    System.out.printf("%s - Pagamento: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
                case 6:
                    valor = random.nextDouble() * 10000; // Valor aleatório até R$ 10.000
                    Investimentos investimentos = new Investimentos(valor);
                    investimentos.realizarInvestimento();
                    System.out.printf("%s - Investimento: R$ %.2f\n", dataOperacao.toString(), valor);
                    break;
            }
        }
    }

    public static void transferirParaContaPoupanca() {
        System.out.println("Digite o valor a ser transferido para a conta poupança:");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (valor > saldo) {
            System.out.println("Saldo insuficiente");
        } else {
            saldo -= valor;
            contaPoupanca.saldo += valor;
            System.out.printf("Transferência de R$%.2f realizada com sucesso para a conta poupança\n", valor);
            extrato.add(String.format("%s - Transferência para Conta Poupança: R$ %.2f", LocalDate.now().toString(), valor));
        }

        System.out.print("Deseja efetuar outra Transferência? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra Transferência? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            transferirParaContaPoupanca();
        }
    }

    private static boolean debitar(double valor) {
        saldo -= valor;
        extrato.add(String.format("%s - Débito: R$ %.2f", LocalDate.now().toString(), valor));
        return false;
    }

    public static void depositar(double valor) {
    }
}