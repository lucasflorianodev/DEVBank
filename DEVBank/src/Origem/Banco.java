package Origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

import java.util.Scanner;

public class Banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===============================");
        System.out.println("DEVBANK");
        System.out.println("===============================");
        System.out.println("Bem-vindo ao DevBank");

        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("\nPor favor, selecione uma opção:");
            System.out.println("1 - Abra sua conta agora");
            System.out.println("2 - Acesse sua conta");
            System.out.print("Opção: ");
            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
                if (opcao == 1) {
                    AberturaContaDigital.abrirNovaConta();
                } else if (opcao == 2) {
                    acessarConta();
                } else {
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    System.out.print("Opção: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, tente novamente.");
                System.out.print("Opção: ");
            }
        }
    }

    public static void acessarConta() {
        Scanner scanner = new Scanner(System.in);

        ClienteContaDigital ClienteContaDigital = null;
        ContaCorrente contaCorrente = null;
        ContaPoupanca contaPoupanca = null;

        while (ClienteContaDigital == null) {
            System.out.println("Por favor, informe o número da sua conta (9 dígitos / 000000-000)");
            System.out.print("Número da Conta:");
            String numeroConta = scanner.nextLine();
            System.out.println("Por favor, informe sua senha (6 dígitos)");
            System.out.print("Senha:");
            String senha = scanner.nextLine();

            try {
                ClienteContaDigital = new ClienteContaDigital(numeroConta, senha);
                contaCorrente = new ContaCorrente(ClienteContaDigital.getSaldoContaCorrente());
                contaPoupanca = new ContaPoupanca(ClienteContaDigital.getSaldoPoupanca());

            } catch (NumeroContaInvalidoException e) {
                System.out.println("\nVerificando suas credenciais");
                System.out.println("Conta inválida. Por favor, verifique o número da conta e tente novamente..");
            } catch (SenhaInvalidaException e) {
                System.out.println("\nVerificando suas credenciais");
                System.out.println("Senha inválida. Por favor, verifique sua senha e tente novamente.");
            } catch (ContaSenhaInvalidaException e) {
                System.out.println("\nVerificando suas credenciais");
                System.out.println("Conta e senha inválida. Por favor, verifique o número da conta e sua senha e tente novamente.");
            }
        }
        System.out.println("\nVerificando suas credenciais");
        System.out.println("Verificação concluída com Sucesso. Acessando sua conta");

        while (true) {

            System.out.println("\n===============================");
            System.out.println(" SERVIÇOS DEVBANK");
            System.out.println("===============================");
            System.out.println("\nPor favor, escolha uma opção:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("3 - Transferências");
            System.out.println("4 - Pix");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Depósito");
            System.out.println("7 - Cartões");
            System.out.println("8 - Investimentos");
            System.out.println("9 - DevEconomy");
            System.out.println("10 - Informe de Rendimentos");
            System.out.println("0 - Encerrar sessão e Desconectar");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcaoServico = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoServico) {
                case 1 -> {
                    System.out.println("Você escolheu a opção Conta Corrente.");
                    ContaCorrente.exibirOpcoesContaCorrente();
                }
                case 2 -> {
                    System.out.println("Você escolheu a opção Conta Poupança.");
                    ContaPoupanca.exibirOpcoesContaPoupanca();
                }
                case 3 -> {
                    System.out.println("Você escolheu a opção Transferências.");
                    Transferencias.exibirOpcoesTransferencias();
                }
                case 4 -> {
                    System.out.println("Você escolheu a opção Pix.");
                    Pix.exibirOpcoesPix();
                }
                case 5 -> {
                    System.out.println("Você escolheu a opção Pagamentos.");
                    Pagamentos.exibirOpcoesPagamentos();
                }
                case 6 -> {
                    System.out.println("Você escolheu a opção Depósito.");
                    Deposito.exibirOpcoesDeposito();
                }
                case 7 -> {
                    System.out.println("Você escolheu a opção Cartões.");
                    Cartoes.exibirOpcoesCartoes();
                }
                case 8 -> {
                    System.out.println("Você escolheu a opção investimentos.");
                    Investimentos.exibirOpcoesInvestimentos();
                }
                case 9 -> {
                    System.out.println("Você escolheu a opção DevEconomy.");
                    DevEconomy.exibirOpcoesDevEconomy();
                }
                case 10 -> {
                    System.out.println("Você escolheu a opção Informes de Rendimento.");
                        InformesRendimento.exibirOpcoesInformeRendimentos();
                }
                case 0 -> {
                    System.out.println("Você escolheu a opção de Encerrar sessão e Desconectar.");
                    System.out.println("Obrigado por utilizar o DevBank. Volte sempre!");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}