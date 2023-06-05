package Origem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static Origem.Cartoes.cdbDevPlus;
import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;

public class Investimentos {
    private static Scanner scanner = new Scanner(System.in);
    static double saldoInvestimentos;
    static List<String> extratoinvest;

    public Investimentos(double saldoInvestimentosInicial) {
        saldoInvestimentos = saldoInvestimentosInicial;
        scanner = new Scanner(System.in);
        extratoinvest = new ArrayList<>();
    }

    public static void exibirOpcoesInvestimentos() { // Remover "static" aqui
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println(" INVESTIMENTOS DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de operação PIX:");
            System.out.println("1 - Consultar Saldo");
            System.out.println("2 - Consultar Rendimentos");
            System.out.println("3 - Consultar Extrato");
            System.out.println("4 - Opções de Investimentos");
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
                case 1 -> consultarSaldoInvest();
                case 2 -> consultarRendimentosInvest();
                case 3 -> consultarExtratoInvest();
                case 4 -> exibirOpcoesAplicacaoInvest();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void consultarSaldoInvest() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldoInvestimentos);
    }


    public static void consultarRendimentosInvest() {
        if (extratoinvest == null) {
            extratoinvest = new ArrayList<>();
        }

        double rendimento = saldoInvestimentos * 0.10;
        saldoInvestimentos += rendimento;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Rendimentos de Investimentos | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                rendimento);
        extratoinvest.add(registro);
        System.out.println(registro);
    }

    public static void consultarExtratoInvest() {
        if (extratoinvest == null) {
            extratoinvest = new ArrayList<>();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        if (extratoinvest.isEmpty()) {
            System.out.println("Não há movimentação disponível.");
            return;
        }

        for (String operacao : extratoinvest) {
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

    private static void exibirOpcoesAplicacaoInvest() {
        while (true) {
            System.out.println("\n===============================");
            System.out.println(" INVESTIMENTOS DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de operação PIX:");
            System.out.println("1 - CDB Dev");
            System.out.println("2 - CDB DevPlus");
            System.out.println("0 - Encerrar operação e voltar para tela inicial Investimentos");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                exibirOpcoesInvestimentos();
                break;
            }

            switch (opcao) {
                case 1 -> cdbDev();
                case 2 -> cdbDevPlus();
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void cdbDev() {
        if (extratoinvest == null) {
            extratoinvest = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor que deseja investir:");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser aplicado como investimento deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Saldo insuficiente para investir.");
                return;

            } else {
                saldocorrente -= valor;
                saldoInvestimentos += valor;

                LocalDateTime dataHora = LocalDateTime.now();
                String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | CDB Dev - Investimento | Valor = R$ %.2f",
                        dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                        dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                        valor);
                extratocorrente.add(registro);
                extratoinvest.add(registro);
                System.out.printf("Investimento realizado com sucesso.\n%s\n", registro);
            }

        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            cdbDev(); // Chama o método novamente para tentar novamente
            return;
        }

        System.out.print("Deseja efetuar outra aplicação? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra aplicação? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            cdbDev();
        }

        if (novaOpcao.equalsIgnoreCase("N")) {
            exibirOpcoesInvestimentos();
        }
    }
}