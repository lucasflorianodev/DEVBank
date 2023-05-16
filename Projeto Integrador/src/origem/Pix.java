package origem;

import java.util.Scanner;

    public class Pix {
        public Pix() {
        }

        public static void realizarPagamentoPix() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("===============================");
                System.out.println("PAGAMENTOS PIX DEVBANK");
                System.out.println("===============================");
                System.out.println("Selecione o tipo de pagamento:");
                System.out.println("1 - Transferir (chaves)");
                System.out.println("2 - Transferir (cópia e cola)");
                System.out.println("3 - Transferir (DevCod)");
                System.out.println("0 - Encerrar operação e voltar para a tela inicial");
                System.out.print("Opção: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Opção inválida, tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 0) {
                    System.out.println("Você escolheu a opção Encerrar operação e voltar para a tela inicial");
                    break;
                }

                if (opcao == 1) {
                    System.out.println("Você escolheu a opção Transferir (chaves).");
                    // Lógica de transferência usando chaves
                    System.out.print("Digite a chave PIX do destinatário: ");
                    String chavePix = scanner.nextLine();

                    System.out.print("Digite o valor da transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Transferência PIX realizada com sucesso.");
                    System.out.println("Valor transferido: R$" + valorTransferencia);
                    System.out.println("Destinatário: " + chavePix);

                    System.out.print("Deseja realizar outro pagamento PIX? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("N")) {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                } else if (opcao == 2) {
                    System.out.println("Você escolheu a opção Transferir (cópia e cola).");
                    // Lógica de transferência usando cópia e cola
                    System.out.print("Cole aqui a informação de pagamento PIX: ");
                    String informacaoPix = scanner.nextLine();

                    System.out.println("Transferência PIX realizada com sucesso.");
                    System.out.println("Informação PIX: " + informacaoPix);

                    System.out.print("Deseja realizar outro pagamento PIX? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("N")) {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                } else if (opcao == 3) {
                    System.out.println("Você escolheu a opção Transferir (DevCod).");
                    // Lógica de transferência usando DevCod
                    System.out.print("Digite o DevCod: ");
                    String devCod = scanner.nextLine();

                    System.out.println("Transferência PIX realizada com sucesso.");
                    System.out.println("DevCod: " + devCod);

                    System.out.print("Deseja realizar outro pagamento PIX? (S/N): ");
                    String novaOpcao = scanner.nextLine();
                    if (novaOpcao.equalsIgnoreCase("N")) {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                } else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }
        }

        public static void realizarCobrancaPix() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("===============================");
                System.out.println("RECEBER PIX DEVBANK");
                System.out.println("===============================");
                System.out.println("Selecione o tipo de cobrança:");
                System.out.println("1 - Receber (chaves)");
                System.out.println("2 - Receber (DevCod)");
                System.out.println("0 - Encerrar operação e voltar para a tela inicial");
                System.out.print("Opção: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Opção inválida, tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 0) {
                    System.out.println("Você escolheu a opção Encerrar operação e voltar para a tela inicial");
                    break;
                }

                if (opcao == 1) {
                    System.out.println("Você escolheu a opção Receber (chaves).");
                    // Lógica de cobrança usando chaves
                    System.out.print("Digite a chave PIX do pagador: ");
                    String chavePix = scanner.nextLine();

                    System.out.print("Digite o valor da cobrança: ");
                    double valorCobranca = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Cobrança PIX realizada com sucesso.");
                    System.out.println("Valor cobrado: R$" + valorCobranca);
                    System.out.println("Pagador: " + chavePix);

                    System.out.print("Deseja realizar outra cobrança PIX? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("N")) {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                } else if (opcao == 2) {
                    System.out.println("Você escolheu a opção Receber (DevCod).");
                    // Lógica de cobrança usando DevCod
                    System.out.print("Digite o DevCod: ");
                    String devCod = scanner.nextLine();

                    System.out.println("Cobrança PIX realizada com sucesso.");
                    System.out.println("DevCod: " + devCod);

                    System.out.print("Deseja realizar outra cobrança PIX? (S/N): ");
                    String novaOpcao = scanner.nextLine();

                    if (novaOpcao.equalsIgnoreCase("N")) {
                        System.out.println("Obrigado por utilizar nossos serviços.");
                        break;
                    }
                } else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }
        }
    }
