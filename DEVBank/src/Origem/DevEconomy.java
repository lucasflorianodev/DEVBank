package Origem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;

public class DevEconomy {
    private static Scanner scanner = new Scanner(System.in);
    static double saldoDevEconomy;
    private static List<String> extratoDev = new ArrayList<>();

    public DevEconomy(double saldoDevEconomyInicial) {
        saldoDevEconomy = saldoDevEconomyInicial;
        scanner = new Scanner(System.in);
        extratoDev = new ArrayList<>();
    }

    public static void exibirOpcoesDevEconomy() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println(" DEVECONOMY DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de operação PIX:");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Rendimentos");
            System.out.println("3 - Consultar Extrato");
            System.out.println("4 - Reserva e Economizar");
            System.out.println("0 - Encerrar operação e voltar para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> consultarSaldoDev();
                case 2 -> consultarRendimentosDev();
                case 3 -> consultarExtratoDev();
                case 4 -> ReservaEconomizar();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void consultarSaldoDev() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldoDevEconomy);
    }

    public static void consultarRendimentosDev() {
        if (extratoDev == null) {
            extratoDev = new ArrayList<>();
        }

        double rendimento = saldoDevEconomy * 0.05;
        saldoDevEconomy += rendimento;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Rendimentos DevEconomy | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                rendimento);
        extratoDev.add(registro);
        System.out.println(registro);
    }

    public static void consultarExtratoDev() {
        if (extratoDev == null) {
            extratoDev = new ArrayList<>();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        if (extratoDev.isEmpty()) {
            System.out.println("Não há movimentação disponível.");
            return;
        }

        for (String operacao : extratoDev) {
            String[] partes = operacao.split(" \\| ");
            if (partes.length < 3) {
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

    public static void ReservaEconomizar() {
        while (true) {
            System.out.println("===============================");
            System.out.println("RESERVA E ECONOMIZAR");
            System.out.println("===============================");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Guardar");
            System.out.println("2 - Retirar");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                exibirOpcoesDevEconomy();
                break;
            }

            switch (opcao) {
                case 1 -> Guardar();
                case 2 -> Retirar();
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void Guardar() {
        System.out.println("Digite o valor a ser depositado na DevEconomy:");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Saldo insuficiente para efetuar o depósito.");
                return;
            }

            saldocorrente -= valor;
            saldoDevEconomy += valor;

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Guardar Dinheiro - DevEconomy | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratocorrente.add(registro);
            extratoDev.add(registro);
            System.out.printf("Depósito realizado com sucesso.\n%s\n", registro);

        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            Guardar(); // Chama o método novamente para tentar novamente
            return;
        }

        System.out.print("Deseja efetuar outro depósito? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outro depósito? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            Guardar();
        }

        if (novaOpcao.equalsIgnoreCase("N")) {
            exibirOpcoesDevEconomy();
        }
    }

    private static void Retirar() {
        System.out.println("Digite o valor a ser retirado da DevEconomy:");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser retirado deve ser maior que zero.");
                return;
            }

            if (valor > saldoDevEconomy) {
                System.out.println("Saldo insuficiente para retirar.");
                return;
            }

            saldoDevEconomy -= valor;
            saldocorrente += valor;

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Retirar Dinheiro - DevEconomy | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratocorrente.add(registro);
            extratoDev.add(registro);
            System.out.printf("Retirada realizada com sucesso.\n%s\n", registro);

        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            Retirar(); // Chama o método novamente para tentar novamente
            return;
        }

        System.out.print("Deseja efetuar outra retirada? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra retirada? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            Retirar();
        }

        if (novaOpcao.equalsIgnoreCase("N")) {
            exibirOpcoesDevEconomy();
        }
    }
}