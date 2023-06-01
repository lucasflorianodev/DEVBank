package Origem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Origem.ContaPoupanca.extratopoupanca;

public class ContaCorrente {
    static double saldocorrente;
    static List<String> extratocorrente = null;
    private static Scanner scanner = null;

    public ContaCorrente(double saldoInicial) {
        saldocorrente = saldoInicial;
        extratocorrente = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void exibirOpcoesContaCorrente() {
        boolean sair = false;
        while (!sair) {
            System.out.println("===============================");
            System.out.println(" CORRENTE DEVBANK");
            System.out.println("===============================");
            System.out.println("\nOpções de Serviço");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Extrato");
            System.out.println("3 - Transferir para Poupança");
            System.out.println("4 - Dev24H - Corrente");
            System.out.println("0 - Encerrar operação e voltar para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> consultarSaldo();
                case 2 -> exibirExtratoCorrente();
                case 3 -> transferirParaContaPoupanca();
                case 4 -> dev24HCorrente();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente");
            }
        }
    }

    public static void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldocorrente);
    }

    public static void exibirExtratoCorrente() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        if (extratocorrente.isEmpty()) {
            System.out.println("Não há movimentação disponível.");
            return;
        }

        for (String operacao : extratocorrente) {
            String[] partes = operacao.split(" \\| ");
            if (partes.length < 2) {
                continue; // Ignora operações inválidas no extrato
            }
            String dataHora = partes[0];
            String descricao = partes[1];
            String Valor = partes[2];

            LocalDateTime dataHoraOperacao;
            try {
                dataHoraOperacao = LocalDateTime.parse(dataHora, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Erro ao converter a data e hora do registro: " + dataHora);
                continue; // Ignora operações com formato de data e hora inválido
            }
            System.out.printf("%s  |  %s | %s\n", dataHoraOperacao.format(formatter), descricao, Valor);
        }
    }

    public static void transferirParaContaPoupanca() {
        System.out.println("Digite o valor a ser transferido para a conta poupança:");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        if (valor > saldocorrente) {
            System.out.println("Transferência não autorizada - Saldo insuficiente");
        } else {
            saldocorrente -= valor;
            ContaPoupanca.saldopoupanca += valor;

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Transferência para Conta Poupança | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratocorrente.add(registro);
            extratopoupanca.add(registro);

            System.out.printf("Transferência autorizada com Sucesso\n%s\n", registro);
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
    private static void dev24HCorrente() {
        boolean sair = false;
        while (!sair) {
            System.out.println("===============================");
            System.out.println(" Dev24H - Poupança");
            System.out.println("===============================");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> depositar();
                case 2 -> sacar();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void depositar() {
        System.out.println("Digite o valor a ser depositado:");

        double valor;
        try {
            valor = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            depositar(); // Chama o método novamente para tentar novamente
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
            return;
        }

        saldocorrente += valor;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Depósito na Conta Poupança | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                valor);
        extratopoupanca.add(registro);
        System.out.printf("Depósito realizado com sucesso.\n%s\n", registro);
    }

    private static void sacar() {
        System.out.println("Digite o valor a ser sacado:");

        double valor;
        try {
            valor = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            sacar(); // Chama o método novamente para tentar novamente
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor inválido, o valor a ser sacado deve ser maior que zero.");
            return;
        }

        if (valor > saldocorrente) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return;
        }

        saldocorrente -= valor;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Saque na Conta Poupança | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                valor);
        extratopoupanca.add(registro);
        System.out.printf("Saque realizado com sucesso.\n%s\n", registro);
    }
}