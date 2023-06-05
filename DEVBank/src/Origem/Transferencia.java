package Origem;

import java.util.Scanner;

public class Transferencia {
    public Transferencia(double valor) {
    }

    public static void realizarTransferencia() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===============================");
            System.out.println("TRANSFERÊNCIAS DEVBANK");
            System.out.println("===============================");
            System.out.println("Selecione o tipo de Transferência:");
            System.out.println("1 - TED");
            System.out.println("2 - DOC");
            System.out.println("3 - Boleto");
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
                System.out.println("Você escolheu a opção TED.");
                // Lógica de transferência por TED
                System.out.print("Digite o valor da transferência: ");
                double valorTransferencia = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Digite o CPF/CNPJ do destinatário: ");
                String cpfCnpjDestinatario = scanner.nextLine();

                System.out.println("Transferência por TED realizada com sucesso.");
                System.out.println("Valor transferido: R$" + valorTransferencia);
                System.out.println("Destinatário: " + cpfCnpjDestinatario);

                System.out.print("Deseja realizar outra transferência? (S/N): ");
                String novaOpcao = scanner.nextLine();

                if (novaOpcao.equalsIgnoreCase("N")) {
                    System.out.println("Obrigado por utilizar nossos serviços.");
                    break;
                }
            } else if (opcao == 2) {
                System.out.println("Você escolheu a opção DOC.");
                // Lógica de transferência por DOC
                System.out.print("Digite o valor da transferência: ");
                double valorTransferencia = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Digite o CPF/CNPJ do destinatário: ");
                String cpfCnpjDestinatario = scanner.nextLine();

                System.out.println("Transferência por DOC realizada com sucesso.");
                System.out.println("Valor transferido: R$" + valorTransferencia);
                System.out.println("Destinatário: " + cpfCnpjDestinatario);

                System.out.print("Deseja realizar outra transferência? (S/N): ");
                String novaOpcao = scanner.nextLine();

                if (novaOpcao.equalsIgnoreCase("N")) {
                    System.out.println("Obrigado por utilizar nossos serviços.");
                    break;
                }
            } else if (opcao == 3) {
                System.out.println("Você escolheu a opção Boleto.");
                // Lógica de emissão de boleto
                System.out.print("Digite o valor do boleto: ");
                double valorBoleto = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Digite o CPF/CNPJ do beneficiário: ");
                String cpfCnpjBeneficiario = scanner.nextLine();
                System.out.println("Boleto emitido com sucesso.");
                System.out.println("Valor do boleto: R$" + valorBoleto);
                System.out.println("Beneficiário: " + cpfCnpjBeneficiario);

                System.out.print("Deseja emitir outro boleto? (S/N): ");
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

    public static void main(String[] args) {
        realizarTransferencia();
    }
}


