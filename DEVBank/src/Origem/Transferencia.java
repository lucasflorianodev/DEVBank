package Origem;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;
import static Origem.Pix.PagamentoTransferenciaPix;

public class Transferencias {
    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirOpcoesTransferencias() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println("TRANSFERÊNCIA DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de Transferência:");
            System.out.println("1 - TED");
            System.out.println("2 - Pix");
            System.out.println("0 - Encerrar operação e voltar para a tela inicial");
            System.out.print("Opção:");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> transferirTED();
                case 2 -> PagamentoTransferenciaPix();
                case 0 -> sair = true;
                default -> System.out.println("\nOpção inválida, tente novamente.");
            }
        }
    }

    private static void transferirTED() {
        System.out.println("\nVocê escolheu a opção TED.");

        System.out.print("Digite a Agência do destinatário: ");
        String AgenciaDestinatario = scanner.nextLine();

        System.out.print("Digite a Conta do destinatário: ");
        String ContaDestinatario = scanner.nextLine();

        System.out.print("Digite o CPF/CNPJ do destinatário: ");
        String cpfCnpjDestinatario = scanner.nextLine();

        System.out.print("Digite o valor da transferência: ");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Transferência não autorizada - Saldo insuficiente");
                return;

            } else {
                saldocorrente -= valor;

                LocalDateTime dataHora = LocalDateTime.now();
                String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Transferência Via TED | Valor = R$ %.2f",
                        dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                        dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                        valor);
                extratocorrente.add(registro);
                System.out.println("\nProcessando Dados da Transferência");
                System.out.println("||||||||||||||");
                System.out.println("||||||||||||||||||||||||||||");
                System.out.printf("Transferência autorizada com Sucesso\n%s\n", registro);
                System.out.println("\n========================================");
                System.out.println("Resumo da Transferência:");
                System.out.println("========================================");
                System.out.println("Agência: " + AgenciaDestinatario);
                System.out.println("Conta: " + ContaDestinatario);
                System.out.println("CPF/CNPJ: "+ cpfCnpjDestinatario);
                System.out.println("Valor: R$" + valor);
            }

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            transferirTED();
            return;
        }

        System.out.print("\nDeseja efetuar outra Transferência? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra Transferência? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            transferirTED();
        }
    }
}
