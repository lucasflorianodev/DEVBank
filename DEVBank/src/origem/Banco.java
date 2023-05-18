package origem;

import exceptions.ContaSenhaInvalidaException;
import exceptions.NumeroContaInvalidoException;
import exceptions.SenhaInvalidaException;

import java.util.Scanner;

import static origem.Pagamento.realizarPagamento;
import static origem.Transferencia.realizarTransferencia;
import static origem.Pix.*;


public class Banco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao DevBank");

        Cliente cliente = null;
        while (cliente == null) {
            System.out.println("Por favor, informe o número da sua conta (9 dígitos / 000000-000)");
            System.out.print("Número da Conta: ");
            String numeroConta = scanner.nextLine();
            System.out.println("Por favor, informe sua senha (6 dígitos)");
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            try {
                cliente = new Cliente(numeroConta, senha);
                ContaCorrente contaCorrente = null;
                ContaPoupanca contaPoupanca = new ContaPoupanca(cliente.getSaldopoupanca(), contaCorrente);
                contaCorrente = new ContaCorrente(cliente.getSaldo(), contaPoupanca);
                cliente.setContaCorrente(contaCorrente);

            } catch (NumeroContaInvalidoException e) {
                System.out.println("Verificando suas credenciais");
                System.out.println("Conta inválida. Por favor, verifique o número da conta e tente novamente..");
            } catch (SenhaInvalidaException e) {
                System.out.println("Verificando suas credenciais");
                System.out.println("Senha inválida. Por favor, verifique sua senha e tente novamente.");
            } catch (ContaSenhaInvalidaException e) {
                System.out.println("Verificando suas credenciais");
                System.out.println("Conta e senha inválida. Por favor, verifique o número da conta e sua senha e tente novamente.");
            }
        }
        System.out.println("Verificando suas credenciais");

        System.out.println("Verificação concluída com Sucesso. Acessando sua conta");

        System.out.println("Olá, " + cliente.getNome() + ". Seu saldo atual é de " + cliente.getSaldoFormatado());

        while (true) {

            System.out.println("===============================");
            System.out.println(" SERVIÇOS DEVBANK");
            System.out.println("===============================");
            System.out.println("Por favor, escolha uma opção:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("3 - Transferências");
            System.out.println("4 - Pagamentos");
            System.out.println("5 - Investimentos");
            System.out.println("6 - Pix");
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

                case 1:
                    System.out.println("Você escolheu a opção de Conta Corrente.");
                    ContaCorrente.exibirOpcoesContaCorrente();
                    break;

                case 2:
                    System.out.println("Você escolheu a Conta Poupança.");
                    ContaPoupanca.exibirOpcoesContaPoupanca();
                    break;

                case 3:
                    System.out.println("Você escolheu a opção de Transferências.");
                    realizarTransferencia();
                    break;

                case 4:
                    System.out.println("Você escolheu a opção de Pagamentos.");
                    realizarPagamento();
                    break;

                case 5:
                    System.out.println("Você escolheu a opção de investimentos.");
                    break;

                case 6:
                    System.out.println("Você escolheu a opção Pix.");
                    System.out.println("Selecione o tipo de operação PIX:");
                    System.out.println("1 - Pagamento");
                    System.out.println("2 - Cobrança");
                    System.out.println("0 - Voltar para o menu principal");
                    System.out.print("Opção: ");

                    int pixOpcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (pixOpcao) {
                        case 1:
                            System.out.println("Você escolheu a opção de realizar um pagamento PIX.");
                            realizarPagamentoPix();
                            break;
                        case 2:
                            System.out.println("Você escolheu a opção de realizar uma cobrança PIX.");
                            realizarCobrancaPix();
                            break;
                        case 0:
                            System.out.println("Você escolheu voltar para o menu principal.");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }

                    break;
                case 0:
                    System.out.println("Você escolheu a opção de Encerrar sessão e Desconectar.");
                    System.out.println("Obrigado por utilizar o DevBank. Volte sempre!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}