package Origem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;
import static Origem.Investimentos.extratoinvest;
import static Origem.Investimentos.saldoInvestimentos;


public class Cartoes {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    static int limiteCredito;
    static final List<String> fatura = new ArrayList<>();
    private static String numeroCartaoVirtual = null;
    private static String codigoSegurancaCartaoVirtual;
    private static boolean CartaoFisicoStatus = false;
    private static boolean CartaoVirtualStatus = false;
    private static boolean CartaoNovoStatus = true;

    public static void exibirOpcoesCartoes() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println("CARTÕES DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Consultar Limite");
            System.out.println("2 - Solicitar Limite");
            System.out.println("3 - Fatura do Cartão");
            System.out.println("4 - Cartão Físico");
            System.out.println("5 - Cartão Virtual");
            System.out.println("6 - CDB DevPlus");
            System.out.println("0 - Encerrar operação e voltar para tela inicial");
            System.out.print("Opção:");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> consultarLimite();
                case 2 -> solicitarLimite();
                case 3 -> consultarFatura();
                case 4 -> CartaoFisico();
                case 5 -> cartaoVirtual();
                case 6 -> cdbDevPlus();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void consultarLimite() {

        if (limiteCredito == 0) {
            System.out.println("\nNão há nenhum limite de crédito disponibilizado.");
            System.out.println("Para ter disponibilidade de limite de crédito, é necessário que faça uma solicitação de limite");
            System.out.println("Você será direcionado para Solicitar Limite.");
            solicitarLimite();

        } else {
            System.out.println("\nLimite atual Disponivel: R$" + limiteCredito);
        }
    }


    public static void solicitarLimite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n----------------------------");
        System.out.println("Solicitar Limite");
        System.out.println("----------------------------");
        System.out.print("\nInforme sua renda mensal: ");
        double rendaMensal = scanner.nextDouble();

        String tipoCartao;
        if (rendaMensal <= 500) {
            tipoCartao = "CLASSIC CARD";
            limiteCredito = 1000;
        } else if (rendaMensal <= 1200) {
            tipoCartao = "OCEAN CARD";
            limiteCredito = 3000;
        } else if (rendaMensal <= 6000) {
            tipoCartao = "SAPPHIRE CARD";
            limiteCredito = 8000;
        } else {
            tipoCartao = "ACQUAMARINE CARD";
            limiteCredito = 25000;
        }

        System.out.println("\nProcessando Dados da Solicitação");
        System.out.println("||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("\n----------------------------");
        System.out.println("Dados da Solicitação");
        System.out.println("----------------------------");
        System.out.println("\nRenda mensal: R$" + rendaMensal);
        System.out.println("Tipo de cartão disponibilizado: " + tipoCartao);
        System.out.println("Limite de crédito disponibilizado: R$" + limiteCredito);
    }

    public static void consultarFatura() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy");

        if (fatura.isEmpty()) {
            System.out.println("Não há movimentação disponível.");
            return;
        }

        for (String operacao : fatura) {
            String[] partes = operacao.split(" \\| ");
            if (partes.length < 3) {
                continue; // Ignora operações inválidas no extrato
            }
            String dataHora = partes[0];
            String descricao = partes[1];
            String valor = partes[2];

            LocalDateTime dataHoraOperacao;
            try {
                dataHoraOperacao = LocalDateTime.parse(dataHora, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Erro ao converter a data e hora do registro: " + dataHora);
                continue; // Ignora operações com formato de data e hora inválido
            }
            System.out.printf("%s  |  %s | %s\n", dataHoraOperacao.format(formatter), descricao, valor);
        }
    }

    public static void cartaoVirtual() {
        while (true) {
            System.out.println("\n----------------------------");
            System.out.println(" CARTÃO VIRTUAL ");
            System.out.println("----------------------------");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Criar cartão virtual");
            System.out.println("2 - Informações do cartão virtual");
            System.out.println("3 - Bloquear/Desbloquear cartão virtual");
            System.out.println("4 - Cancelar cartão virtual");
            System.out.println("0 - Voltar para Tela inicial Cartões");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                exibirOpcoesCartoes();
                break;
            }

            switch (opcao) {
                case 1 -> criarCartaoVirtual();
                case 2 -> exibirInformacoesCartaoVirtual();
                case 3 -> StatusCartaoVirtual();
                case 4 -> cancelarCartaoVirtual();
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static boolean cartaoVirtualCriado = false;

    private static void criarCartaoVirtual() {
        if (cartaoVirtualCriado) {
            System.out.println("Você já possui um cartão virtual.");
            return;
        }

        numeroCartaoVirtual = gerarNumeroCartaoVirtual(); // Gera o número do cartão virtual
        codigoSegurancaCartaoVirtual = gerarCodigoSeguranca(); // Gera o código de segurança

        String numeroFormatado = numeroCartaoVirtualFormatado(numeroCartaoVirtual);

        System.out.println("Cartão virtual criado com sucesso!");
        System.out.println("Número do cartão: " + numeroFormatado);
        System.out.println("Dígito de segurança: " + codigoSegurancaCartaoVirtual);

        cartaoVirtualCriado = true;
    }



    private static String numeroCartaoVirtualFormatado(String numeroCartaoVirtual) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numeroCartaoVirtual.length(); i++) {
            builder.append(numeroCartaoVirtual.charAt(i));
            if ((i + 1) % 4 == 0 && i != numeroCartaoVirtual.length() - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    private static String gerarNumeroCartaoVirtual() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
    }

    private static String gerarCodigoSeguranca() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
        return builder.toString();
    }

    private static void exibirInformacoesCartaoVirtual() {
        if (!cartaoVirtualCriado) {
            System.out.println("Você ainda não possui um cartão virtual.");
            return;
        }

        System.out.println("Informações do cartão virtual:");
        System.out.println("Número do cartão: " + numeroCartaoVirtualFormatado(numeroCartaoVirtual));
        System.out.println("Dígito de segurança: " + codigoSegurancaCartaoVirtual);
    }

    private static void cancelarCartaoVirtual() {
        if (!cartaoVirtualCriado) {
            System.out.println("Você ainda não possui um cartão virtual.");
            return;
        }

        cartaoVirtualCriado = false;
        numeroCartaoVirtual = null;

        System.out.println("Cartão virtual cancelado com sucesso!");
    }

    private static void StatusCartaoVirtual() {
        CartaoVirtualStatus = !CartaoVirtualStatus;
        String status = CartaoVirtualStatus ? "bloqueado" : "desbloqueado";
        System.out.println("Cartão Virtual " + status + " com sucesso!");
    }

    private static void CartaoFisico() {
        while (true) {
            System.out.println("\n----------------------------");
            System.out.println(" CARTÃO FÍSICO ");
            System.out.println("----------------------------");
            System.out.println("\nSelecione uma opção:");
            System.out.println("1 - Informações do Cartão");
            System.out.println("2 - Solicitar 2ª Via do Cartão");
            System.out.println("3 - Desbloquear Cartão Novo");
            System.out.println("4 - Bloquear/Desbloquear Cartão");
            System.out.println("0 - Voltar para Tela inicial Cartões");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                exibirOpcoesCartoes();
                break;
            }

            switch (opcao) {
                case 1 -> exibirInformacoesCartao();
                case 2 -> solicitarSegundaVia();
                case 3 -> desbloquearCartaoNovo();
                case 4 -> StatusCartaoFisico();
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirInformacoesCartao() {
        System.out.println("Informações do Cartão Físico:");
        System.out.println("Número: 1234 5678 9012 3456");
        System.out.println("Data de Validade: 10/24");
        System.out.println("CVV: 123");
        System.out.println("Status: " + (CartaoFisicoStatus ? "Bloqueado" : "Desbloqueado"));
    }

    private static void solicitarSegundaVia() {
        if (CartaoFisicoStatus) {
            System.out.println("Você precisa bloquear o cartão antigo antes de solicitar a 2° Via.");
            return;
        }

        System.out.println("Solicitação de 2ª Via do Cartão Físico realizada com sucesso!");
    }

    private static void desbloquearCartaoNovo() {
        if (!CartaoNovoStatus) {
            System.out.println("O novo cartão já está desbloqueado.");
            return;
        }

        CartaoNovoStatus = false;
        System.out.println("Cartão novo desbloqueado com sucesso!");
    }

    private static void StatusCartaoFisico() {
        CartaoFisicoStatus = !CartaoFisicoStatus;
        String status = CartaoFisicoStatus ? "desbloqueado" : "bloqueado";
        System.out.println("Cartão físico " + status + " com sucesso!");
    }


    public static void cdbDevPlus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor a ser utilizado para aumentar o limite de crédito:");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser usado como limite de crédito deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Saldo insuficiente para aumentar limite de crédito.");
                return;

            } else {
                saldocorrente -= valor;
                limiteCredito += valor;
                saldoInvestimentos += valor;

                LocalDateTime dataHora = LocalDateTime.now();
                String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | CDB DevPlus - Aumento de Limite de Crédito | Valor = R$ %.2f",
                        dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                        dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                        valor);
                extratocorrente.add(registro);
                extratoinvest.add(registro);
                System.out.printf("Aumento de Limite de Crédito realizado com sucesso.\n%s\n", registro);
            }

        } catch (InputMismatchException e) {
            System.out.println("Valor inválido, digite um valor numérico.");
            scanner.nextLine(); // Consome a entrada inválida
            cdbDevPlus(); // Chama o método novamente para tentar novamente
            return;
        }

        System.out.print("Deseja efetuar outra operação? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra operação? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            cdbDevPlus();
        }
    }
}