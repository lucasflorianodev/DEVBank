package Origem;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaPoupanca.extratopoupanca;

public class Deposito {

    public static void exibirOpcoesDeposito() {
        boolean sair = false;
        Scanner scanner = new Scanner(System.in);

        while (!sair) {
            System.out.println("\n===============================");
            System.out.println("DEPÓSITO DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Boleto Conta Corrente");
            System.out.println("2 - Boleto Conta Poupança");
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
                case 1 -> gerarBoletoCorrente();
                case 2 -> gerarBoletoPoupanca();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerarBoletoCorrente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor do depósito: ");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            String boleto = gerarCodigoBarras();

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Depósito Via Boleto Conta Corrente - Solicitação Aberta | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratocorrente.add(registro);
            System.out.println("\nProcessando Dados do Boleto");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.printf("Boleto gerado com Sucesso\n%s\n", registro);
            System.out.println("\n========================================");
            System.out.println("Resumo do Boleto:");
            System.out.println("========================================");
            System.out.println("Operação: Depósito Via Boleto Conta Corrente - Aguardando Pagamento");
            System.out.println("Código de Barras: " + boleto);
            System.out.println("Valor: R$" + valor);

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            gerarBoletoPoupanca();
            return;
        }

        System.out.print("\nDeseja criar outro Depósito? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja criar outro Depósito? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            exibirOpcoesDeposito();
        }
    }

    private static void gerarBoletoPoupanca() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor do depósito: ");
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            String boleto = gerarCodigoBarras();

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Depósito Via Boleto Conta Poupança - Solicitação Aberta | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratopoupanca.add(registro);
            System.out.println("\nProcessando Dados do Boleto");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.printf("Boleto gerado com Sucesso\n%s\n", registro);
            System.out.println("\n========================================");
            System.out.println("Resumo do Boleto:");
            System.out.println("========================================");
            System.out.println("Operação: Depósito Via Boleto Conta Poupança - Aguardando Pagamento");
            System.out.println("Código de Barras: " + boleto);
            System.out.println("Valor: R$" + valor);

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            gerarBoletoPoupanca();
            return;
        }

        System.out.print("\nDeseja criar outro Depósito? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja criar outro Depósito? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            exibirOpcoesDeposito();
        }
    }

    private static String gerarCodigoBarras() {
        StringBuilder codigoBarras = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 48; i++) {
            codigoBarras.append(random.nextInt(10));
        }

        return codigoBarras.toString();
    }
}