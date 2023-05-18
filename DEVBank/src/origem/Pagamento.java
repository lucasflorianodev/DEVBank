package origem;

import java.util.Scanner;

public class Pagamento {
    public Pagamento() {
    }

    public static void realizarPagamento() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===============================");
            System.out.println(" PAGAMENTOS DEVBANK");
            System.out.println("===============================");
            System.out.println("Selecione o tipo de Pagamento;");
            System.out.println("1 - Débito");
            System.out.println("2 - Crédito");
            System.out.println("0 - Encerrar operação e volta para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0){
                System.out.println("Você escolheu a opção Encerrar operação e volta para tela inicial");
                break;
            }

            if (opcao == 1) {
                System.out.println("Você escolheu a opção Débito de Pagamento.");
                System.out.print("Digite o código de barras (48 dígitos): ");
                String codigoBarras = scanner.nextLine();

                if (codigoBarras.length() != 48) {
                    System.out.println("Processando Dados de Pagamento");
                    System.out.println("Código de barras inválido. Verifique os dígitos do Código de barras e Tente Novamente.");
                    continue;
                }

                System.out.println("Processando Dados de Pagamento");

                // Processamento do pagamento
                double valor = Math.random() * 10000;
                double juros = valor * 0.01;
                double multa = valor * 0.005;
                double desconto = valor * 0.0025;
                double valorTotal = valor + juros + multa - desconto;

                System.out.printf("Valor do pagamento: R$ %.2f\n", valor);
                System.out.printf("Resumo da conta:\nJuros: R$ %.2f\nMulta: R$ %.2f\nDesconto: R$ %.2f\nValor total: R$ %.2f\n",
                        juros, multa, desconto, valorTotal);

                System.out.print("Pagar R$ " + String.format("%.2f", valorTotal) + "? (S/N) ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("Processando Dados de Pagamento");

                    if (Math.random() < 0.9) {
                        System.out.println("Pagamento autorizado com Sucesso.");

                        System.out.print("Deseja efetuar outro Pagamento? (S/N): ");
                        String novaOpcao = scanner.nextLine();

                        if (novaOpcao.equalsIgnoreCase("S")) {
                            continue;
                        } else {
                            System.out.println("Obrigado por utilizar nossos serviços.");
                            break;
                        }
                    } else {
                        System.out.println("Pagamento não autorizado. Verifique o saldo em sua conta corrente e tente novamente.");
                    }
                } else if (resposta.equalsIgnoreCase("N")) {
                    System.out.println("Pagamento cancelado.");

                    System.out.print("Deseja efetuar outro Pagamento? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("S")) {
                        continue;
                    } else {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                }
            } else if (opcao == 2) {
                System.out.println("Você escolheu a opção Crédito de Pagamento.");
                System.out.print("Digite o código de barras (48 dígitos): ");
                String codigoBarras = scanner.nextLine();

                if (codigoBarras.length() != 48) {
                    System.out.println("Processando Dados de Pagamento");
                    System.out.println("Código de barras inválido. Verifique os dígitos do Código de barras e Tente Novamente.");
                    continue;
                }

                System.out.println("Processando Dados de Pagamento");

                double valor = Math.random() * 1000;
                double juros = valor * 0.01;
                double multa = valor * 0.005;
                double desconto = valor * 0.0025;
                double valorTotal = valor + juros + multa - desconto;

                System.out.println("Resumo da conta:");
                System.out.printf("Valor do pagamento: R$ %.2f\n", valor);
                System.out.printf("Juros: R$ %.2f\n", juros);
                System.out.printf("Multa: R$ %.2f\n", multa);
                System.out.printf("Desconto: R$ %.2f\n", desconto);
                System.out.printf("Valor total: R$ %.2f\n", valorTotal);

                System.out.print("Deseja Continuar o Pagamento pela Modalidade Crédito (S/N)? ");
                String opcaoparcelamento = scanner.nextLine();

                if (opcaoparcelamento.equalsIgnoreCase("S")) {
                    System.out.println("Deseja fazer o parcelamento em quantas vezes?");
                    System.out.println("1 - 1x (" + String.format("%.2f", valorTotal) + ")");
                    System.out.println("2 - 2x (" + String.format("%.2f", valorTotal / 2) + ")");
                    System.out.println("3 - 3x (" + String.format("%.2f", valorTotal / 3) + ")");
                    System.out.println("4 - 4x (" + String.format("%.2f", valorTotal / 4) + ")");
                    System.out.println("5 - 5x (" + String.format("%.2f", valorTotal / 5) + ")");
                    System.out.println("6 - 6x (" + String.format("%.2f", valorTotal / 6) + ")");
                    System.out.print("Opção de Parcelamento: ");
                    String parcelasOpcao = scanner.nextLine();

                    int parcelas = 0;
                    double valorParcelado = 0;

                    switch (parcelasOpcao) {
                        case "1" -> {
                            parcelas = 1;
                            valorParcelado = valorTotal;
                        }case "2" -> {
                            parcelas = 2;
                            valorParcelado = valorTotal / 2;
                        }
                        case "3" -> {
                            parcelas = 3;
                            valorParcelado = valorTotal / 3;
                        }
                        case "4" -> {
                            parcelas = 4;
                            valorParcelado = valorTotal / 4;
                        }
                        case "5" -> {
                            parcelas = 5;
                            valorParcelado = valorTotal / 5;
                        }
                        case "6" -> {
                            parcelas = 6;
                            valorParcelado = valorTotal / 6;
                        }
                        default -> {
                            System.out.println("Opção inválida. Pagamento cancelado.");
                            scanner.close();
                            return;
                        }
                    }

                    System.out.print("Pagar R$ " + String.format("%.2f", valorTotal) + " em " + parcelas + "x de R$ " + String.format("%.2f", valorParcelado) + "? (S/N) ");
                    String confirmacao = scanner.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        System.out.println("Processando Dados de Pagamento");

                        System.out.print("Digite o código de segurança do cartão - CVV (3 Dígitos): ");
                        String cvv = scanner.nextLine();

                        if (cvv.length() != 3) {
                            System.out.println("Pagamento não autorizado. CVV inválido, Verifique e Tente novamente.");
                            continue;
                        }
                        if (Math.random() < 0.9) {
                            System.out.println("Pagamento autorizado com Sucesso.");

                            System.out.print("Deseja efetuar outro Pagamento? (S/N): ");
                            String novaOpcao = scanner.nextLine();

                            if (novaOpcao.equalsIgnoreCase("S")) {
                                continue;
                            } else {
                                System.out.println("Obrigado por utilizar nossos serviços.");
                                break;
                            }
                        } else {
                            System.out.println("Pagamento não autorizado. Verifique o Limite do seu Cartão de Crédito e tente novamente.");
                        }

                    } else if (confirmacao.equalsIgnoreCase("N" )) {
                        System.out.println("Pagamento cancelado.");

                        System.out.print("Deseja efetuar outro Pagamento? (S/N): ");
                        String novaOpcao = scanner.nextLine();

                        if (novaOpcao.equalsIgnoreCase("S")) {
                            continue;
                        } else {
                            System.out.println("Obrigado por utilizar nossos serviços.");
                            break;
                        }
                    } else {
                        System.out.println("Opção inválida.");
                        continue;
                    }
                }
            }
        }
    }
}