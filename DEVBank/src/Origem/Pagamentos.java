package Origem;

import java.time.LocalDateTime;
import java.util.Scanner;

import static Origem.Cartoes.fatura;
import static Origem.Cartoes.limiteCredito;
import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;

public class Pagamentos {
    private static final int CODIGO_BARRAS_DIGITOS = 48;
    private static final double JUROS = 0.01;
    private static final double MULTA = 0.005;
    private static final double DESCONTO = 0.0025;

    public static void exibirOpcoesPagamentos() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===============================");
            System.out.println(" PAGAMENTOS DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de Pagamento;");
            System.out.println("1 - Débito");
            System.out.println("2 - Crédito");
            System.out.println("0 - Encerrar operação e voltar para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Você escolheu a opção Encerrar operação e voltar para tela inicial");
                break;
            }

            switch (opcao) {
                case 1 -> processarOpcaoDebito();
                case 2 -> processarOpcaoCredito();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void processarOpcaoDebito() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Você escolheu a opção Débito de Pagamento.");
        System.out.print("Digite o código de barras (" + CODIGO_BARRAS_DIGITOS + " dígitos): ");
        String codigoBarras = scanner.nextLine();

        if (codigoBarras.length() != CODIGO_BARRAS_DIGITOS) {
            System.out.println("\nProcessando Dados do Código de barras");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.println("Código de barras inválido. Verifique os dígitos do Código de barras e Tente Novamente.");
            return;
        }
        double valor = gerarValorBoletoAleatorio();
        ProcessarPagamentoDebito(valor, scanner);
    }

    private static void processarOpcaoCredito() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Você escolheu a opção Crédito de Pagamento.");
        System.out.print("Digite o código de barras (" + CODIGO_BARRAS_DIGITOS + " dígitos): ");
        String codigoBarras = scanner.nextLine();

        if (codigoBarras.length() != CODIGO_BARRAS_DIGITOS) {
            System.out.println("\nProcessando Dados do Código de barras");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.println("Código de barras inválido. Verifique os dígitos do Código de barras e Tente Novamente.");
            return;
        }
        double valor = gerarValorBoletoAleatorio();
        ProcessarPagamentoCredito(valor, scanner);
    }

    private static double gerarValorBoletoAleatorio() {
        return Math.random() * 1000;
    }

    private static void ProcessarPagamentoDebito(double valor, Scanner scanner) {

        if (valor > saldocorrente) {
            System.out.println("\nProcessando Dados do Código de barras");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.println("\nSaldo insuficiente para efetuar o Pagamento.");
            return;
        }

        saldocorrente -= valor;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Pagamento - Via Débito | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                valor);
        extratocorrente.add(registro);

        double juros = valor * JUROS;
        double multa = valor * MULTA;
        double desconto = valor * DESCONTO;
        double valorTotal = valor + juros + multa - desconto;

        System.out.println("\nProcessando Dados do Código de barras");
        System.out.println("||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("===============================");
        System.out.println(" RESUMO DO PAGAMENTO");
        System.out.println("===============================");
        System.out.printf("\nJuros: R$ %.2f\nMulta: R$ %.2f\nDesconto: R$ %.2f\nValor total: R$ %.2f\n", juros, multa, desconto, valorTotal);

        System.out.print("\nPagar R$ " + String.format("%.2f", valorTotal) + "? (S/N):");
        String resposta = "";
        boolean respostaValida = false;

        while (!respostaValida) {
            resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
                respostaValida = true;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                System.out.print("Opções (S/N):");
            }
        }

        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("\nProcessando Dados do Pagamento");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.printf("Pagamento realizado com sucesso.\n%s\n", registro);

            System.out.print("\nDeseja efetuar outro Pagamento? (S/N): ");
            String novaOpcao = scanner.nextLine();

            while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
                System.out.println("Opção inválida. Digite novamente.");
                System.out.print("Deseja efetuar outro Pagamento? (S/N): ");
                novaOpcao = scanner.nextLine();
            }
            if (novaOpcao.equalsIgnoreCase("S")) {
                processarOpcaoDebito();
            }
            if (novaOpcao.equalsIgnoreCase("N")) {
                exibirOpcoesPagamentos();
            }

        } else if (resposta.equalsIgnoreCase("N")) {
            System.out.println("\nPagamento cancelado.");

            System.out.print("\nDeseja efetuar outro Pagamento? (S/N): ");
            String novaOpcao = scanner.nextLine();

            if (novaOpcao.equalsIgnoreCase("S")) {
                processarOpcaoDebito();
            } else {
                exibirOpcoesPagamentos();
            }
        }

    }

    private static void ProcessarPagamentoCredito(double valor, Scanner scanner) {
        if (valor > limiteCredito) {
            System.out.println("\nProcessando Dados do Código de barras");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.println("\nLimite de Crédito insuficiente para efetuar o Pagamento, Verifique o Limite de Crédito e tente novamente.");
            return;
        }

        limiteCredito -= valor;

        LocalDateTime dataHora = LocalDateTime.now();
        String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Pagamento - Via Crédito | Valor = R$ %.2f",
                dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                valor);
        fatura.add(registro);

        double juros = valor * JUROS;
        double multa = valor * MULTA;
        double desconto = valor * DESCONTO;
        double valorTotal = valor + juros + multa - desconto;

        System.out.println("\nProcessando Dados do Código de barras");
        System.out.println("||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("===============================");
        System.out.println(" RESUMO DO PAGAMENTO");
        System.out.println("===============================");
        System.out.printf("\nJuros: R$ %.2f\nMulta: R$ %.2f\nDesconto: R$ %.2f\nValor total: R$ %.2f\n", juros, multa, desconto, valorTotal);

        System.out.print("\nPagar R$ " + String.format("%.2f", valorTotal) + " no cartão de crédito? (S/N):");
        String resposta = "";
        boolean respostaValida = false;

        while (!respostaValida) {
            resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
                respostaValida = true;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                System.out.print("Opções (S/N):");
            }
        }

        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Deseja fazer o parcelamento em quantas vezes?");
            System.out.println("1 - 1x (" + String.format("%.2f", valor) + ")");
            System.out.println("2 - 2x (" + String.format("%.2f", valor / 2) + ")");
            System.out.println("3 - 3x (" + String.format("%.2f", valor / 3) + ")");
            System.out.println("4 - 4x (" + String.format("%.2f", valor / 4) + ")");
            System.out.println("5 - 5x (" + String.format("%.2f", valor / 5) + ")");
            System.out.println("6 - 6x (" + String.format("%.2f", valor / 6) + ")");
            System.out.print("Opção de Parcelamento: ");
            String parcelasOpcao = scanner.nextLine();

            int parcelas;
            double valorParcelado;

            switch (parcelasOpcao) {
                case "1" -> {
                    parcelas = 1;
                    valorParcelado = valor;
                }
                case "2" -> {
                    parcelas = 2;
                    valorParcelado = valor / 2;
                }
                case "3" -> {
                    parcelas = 3;
                    valorParcelado = valor / 3;
                }
                case "4" -> {
                    parcelas = 4;
                    valorParcelado = valor / 4;
                }
                case "5" -> {
                    parcelas = 5;
                    valorParcelado = valor / 5;
                }
                case "6" -> {
                    parcelas = 6;
                    valorParcelado = valor / 6;
                }
                default -> {
                    System.out.println("Opção inválida. Pagamento cancelado.");
                    return;
                }
            }

            System.out.print("Pagar R$ " + String.format("%.2f", valor) + " em " + parcelas + "x de R$ " + String.format("%.2f", valorParcelado) + "? (S/N) ");
            String confirmacao = scanner.nextLine();

            while (!confirmacao.equalsIgnoreCase("S") && !confirmacao.equalsIgnoreCase("N")) {
                System.out.println("Opção inválida. Digite novamente.");
                System.out.print("\nDeseja efetuar o Pagamento? (S/N): ");
                confirmacao = scanner.nextLine();
            }

            if (confirmacao.equalsIgnoreCase("S")) {
                System.out.println("\nProcessando Dados do Pagamento");
                System.out.println("||||||||||||||");
                System.out.println("||||||||||||||||||||||||||||");
                System.out.print("Digite o código de segurança do cartão - CVV (3 Dígitos): ");
                String cvv = scanner.nextLine();

                if (cvv.length() != 3) {
                    System.out.println("\nProcessando Dados de Segurança");
                    System.out.println("||||||||||||||");
                    System.out.println("||||||||||||||||||||||||||||");
                    System.out.println("Pagamento não autorizado. CVV inválido, Verifique e Tente novamente.");
                    return;
                }

                if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("\nProcessando Dados do Pagamento");
                    System.out.println("||||||||||||||");
                    System.out.println("||||||||||||||||||||||||||||");
                    System.out.printf("Pagamento realizado com sucesso.\n%s\n", registro);

                    System.out.print("\nDeseja efetuar outro Pagamento? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("S")) {
                        processarOpcaoCredito();
                    } else {
                        exibirOpcoesPagamentos();
                    }
                } else if (resposta.equalsIgnoreCase("N")) {
                    System.out.println("\nPagamento cancelado.");

                    System.out.print("\nDeseja efetuar outro Pagamento? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("S")) {
                        processarOpcaoCredito();
                    } else {
                        exibirOpcoesPagamentos();
                    }
                }
            }

        }
    }
}