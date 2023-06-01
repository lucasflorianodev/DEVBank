package Origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

import java.util.Scanner;

import static Origem.Pix.realizarCobrancaPix;
import static Origem.Pix.realizarPagamentoPix;
import static Origem.Transferencia.realizarTransferencia;

public class Banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao DevBank");
        ClienteContaDigital ClienteContaDigital = null;
        ContaCorrente contaCorrente = null;
        ContaPoupanca contaPoupanca = null;

        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("Por favor, selecione uma opção:");
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
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    public static ClienteContaDigital acessarConta() {
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

        System.out.println("\nOlá, " + ClienteContaDigital.getApelido() + ". Seu saldo atual em sua Conta Corrente é de " + ClienteContaDigital.getSaldoContaCorrenteFormatado());


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
            System.out.println("10 - Informes de Rendimento");
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
                    realizarTransferencia();
                }
                case 4 -> {
                    System.out.println("Você escolheu a opção Pix.");
                    System.out.println("Selecione o tipo de operação PIX:");
                    System.out.println("1 - Pagamento");
                    System.out.println("2 - Cobrança");
                    System.out.println("0 - Voltar para o menu principal");
                    System.out.print("Opção: ");
                    int pixOpcao = scanner.nextInt();
                    scanner.nextLine();
                    switch (pixOpcao) {
                        case 1 -> {
                            System.out.println("Você escolheu a opção de realizar um pagamento PIX.");
                            realizarPagamentoPix();
                        }
                        case 2 -> {
                            System.out.println("Você escolheu a opção de realizar uma cobrança PIX.");
                            realizarCobrancaPix();
                        }
                        case 0 -> System.out.println("Você escolheu voltar para o menu principal.");
                        default -> System.out.println("Opção inválida.");
                    }
                }
                case 5 -> {
                    System.out.println("Você escolheu a opção Pagamentos.");
                    Pagamento.exibirOpcoesPagamento();
                }
                case 6 -> {
                    System.out.println("Você escolheu a opção Depósito.");
                    Investimentos.exibirOpcoesInvestimentos();
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
                    InformesRendimento.exibirOpcoesInformesRendimento();
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