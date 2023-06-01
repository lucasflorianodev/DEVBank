package Origem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Origem.ContaCorrente.extratocorrente;

public class ContaPoupanca {
    static double saldopoupanca;
    static List<String> extratopoupanca = null;
    private static Scanner scanner = null;

    public ContaPoupanca(double saldopoupancaInicial) {
        saldopoupanca = saldopoupancaInicial;
        extratopoupanca = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void exibirOpcoesContaPoupanca() {
        boolean sair = false;
        while (!sair) {
            System.out.println("===============================");
            System.out.println(" POUPANÇA DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Rendimentos");
            System.out.println("3 - Consultar Extrato");
            System.out.println("4 - Transferir para Corrente");
            System.out.println("5 - Dev24H - Poupança");
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
                case 1 -> consultarSaldo();
                case 2 -> calcularRendimento();
                case 3 -> exibirExtratoPoupanca();
                case 4 -> transferirParaContaCorrente();
                case 5 -> dev24HPoupanca();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldopoupanca);
    }

    private static void calcularRendimento() {
        double rendimento = saldopoupanca * 0.02;
        saldopoupanca += rendimento;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Rendimento da Conta Poupança | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                rendimento);
        extratopoupanca.add(registro);
        System.out.println(registro);
    }

    private static void exibirExtratoPoupanca() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        if (extratopoupanca.isEmpty()) {
            System.out.println("Não há movimentação disponível.");
            return;
        }

        for (String operacao : extratopoupanca) {
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

    private static void transferirParaContaCorrente() {
        System.out.println("Digite o valor a ser transferido para a conta corrente:");

        double valor;
        try {
            valor = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Console a entrada inválida
            transferirParaContaCorrente(); // Chama o método novamente para tentar novamente
            return;
        }

        if (valor <= 0) {
            System.out.println("Valor inválido, o valor a ser transferido deve ser maior que zero.");
            return;
        }

        if (valor > saldopoupanca) {
            System.out.println("Transferência não autorizada - Saldo insuficiente");
            return;

        } else {
            saldopoupanca -= valor;
            ContaCorrente.saldocorrente += valor;

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Transferência para Conta Corrente | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratopoupanca.add(registro);
            extratocorrente.add(registro);
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
            transferirParaContaCorrente();
        }
    }
    private static void dev24HPoupanca() {
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

        saldopoupanca += valor;

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

        if (valor > saldopoupanca) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return;
        }

        saldopoupanca -= valor;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Saque na Conta Poupança | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                valor);
        extratopoupanca.add(registro);
        System.out.printf("Saque realizado com sucesso.\n%s\n", registro);
    }
}