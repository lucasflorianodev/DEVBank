package Origem;

import java.util.Scanner;

import static Origem.Banco.acessarConta;

public class AberturaContaDigital {

    public static void abrirNovaConta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Iniciando processo de abertura de conta digital");
        System.out.println("||||||||||||||");
        System.out.println("||||||||||||||||||||||||||||");
        System.out.println("\n==============================================");
        System.out.println("DevBank - Abertura de Conta Digital");
        System.out.println("==============================================");
        System.out.println("\nPor favor, informe as seguintes informações:");

        // solicita o nome completo e valida que somente letras foram digitadas
        System.out.print("Digite seu nome completo:");
        String nomeCompleto = scanner.nextLine();
        while (!nomeCompleto.matches("^[A-Za-z\\s]+$")) {
            System.out.print("Por favor, digite somente letras: ");
            nomeCompleto = scanner.nextLine();
        }

        // solicita o apelido
        System.out.print("Digite seu apelido:");
        String apelido = scanner.nextLine();

        // solicita a nacionalidade e valida que somente letras foram digitadas
        System.out.print("Digite sua nacionalidade:");
        String nacionalidade = scanner.nextLine();
        while (!nacionalidade.matches("^[A-Za-z\\s]+$")) {
            System.out.print("Por favor, digite somente letras: ");
            nacionalidade = scanner.nextLine();
        }

        // solicita o CPF e valida que somente números foram digitados no formato "000.000.000-00"
        System.out.print("Digite seu CPF (no formato 000.000.000-00):");
        String cpf = scanner.nextLine();
        while (!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            System.out.print("Por favor, digite o CPF no formato correto: ");
            cpf = scanner.nextLine();
        }

        // solicita a data de nascimento e valida que somente números foram digitados no formato "00/00/0000"
        System.out.print("Digite sua data de nascimento (no formato dd/mm/aaaa):");
        String dataNascimento = scanner.nextLine();
        while (!dataNascimento.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            System.out.print("Por favor, digite a data de nascimento no formato correto:");
            dataNascimento = scanner.nextLine();
        }

        // solicita o telefone ou celular e valida que somente números foram digitados no formato "(00)90000-0000"
        System.out.print("Digite seu telefone ou celular (no formato (00)90000-0000):");
        String telefoneCelular = scanner.nextLine();
        while (!telefoneCelular.matches("^\\(\\d{2}\\)\\d{5}-\\d{4}$")) {
            System.out.print("Por favor, digite o telefone ou celular no formato correto:");
            telefoneCelular = scanner.nextLine();
        }

        // solicita o e-mail
        System.out.print("Digite seu e-mail:");
        String email = scanner.nextLine();

        // solicita a ocupação profissional
        System.out.print("Digite sua ocupação profissional:");
        String ocupacaoProfissional = scanner.nextLine();

        // solicita a renda mensal e valida que somente números foram digitados
        System.out.print("Digite sua renda mensal:");
        String rendaMensal = scanner.nextLine();
        while (!rendaMensal.matches("^\\d+$")) {
            System.out.print("Por favor, digite somente números: ");
            rendaMensal = scanner.nextLine();
        }

        // Imprime as informações fornecidas pelo cliente
        System.out.println("Resumo das informações:");
        System.out.println("Nome completo: " + nomeCompleto);
        System.out.println("Apelido: " + apelido);
        System.out.println("Nacionalidade: " + nacionalidade);
        System.out.println("CPF: " + cpf);
        System.out.println("Data de nascimento: " + dataNascimento);
        System.out.println("Telefone ou celular: " + telefoneCelular);
        System.out.println("E-mail: " + email);
        System.out.println("Ocupação profissional: " + ocupacaoProfissional);
        System.out.println("Renda mensal: " + rendaMensal);

        // Pergunta se o cliente deseja salvar as alterações
        System.out.println("\nDeseja alterar as informações? (S/N):");
        String resposta = scanner.nextLine();

        // Verifica se a resposta diferente de "S" ou "N"
        while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, Tente Novamente com S/N.");
            resposta = scanner.nextLine();
        }

        while (resposta.equalsIgnoreCase("S")) {
            System.out.println("Qual informação deseja alterar?");
            System.out.println("1 - Nome completo");
            System.out.println("2 - Apelido");
            System.out.println("3 - Nacionalidade");
            System.out.println("4 - CPF");
            System.out.println("5 - Data de nascimento");
            System.out.println("6 - Telefone ou celular");
            System.out.println("7 - E-mail");
            System.out.println("8 - Ocupação profissional");
            System.out.println("9 - Renda mensal");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite seu nome completo:");
                    nomeCompleto = scanner.nextLine();
                    while (!nomeCompleto.matches("^[A-Za-z\\s]+$")) {
                        System.out.print("Por favor, digite somente letras: ");
                        nomeCompleto = scanner.nextLine();
                    }
                }
                case 2 -> {
                    System.out.print("Digite seu apelido: ");
                    apelido = scanner.nextLine();
                }
                case 3 -> {
                    System.out.print("Digite sua nacionalidade:");
                    nacionalidade = scanner.nextLine();
                    while (!nacionalidade.matches("^[A-Za-z\\s]+$")) {
                        System.out.print("Por favor, digite somente letras: ");
                        nacionalidade = scanner.nextLine();
                    }
                }
                case 4 -> {
                    System.out.print("Digite seu CPF (no formato 000.000.000-00):");
                    cpf = scanner.nextLine();
                }
                case 5 -> {
                    System.out.print("Digite sua data de nascimento (no formato dd/mm/aaaa):");
                    dataNascimento = scanner.nextLine();
                }
                case 6 -> {
                    System.out.print("Digite seu telefone ou celular (no formato (00)90000-0000):");
                    telefoneCelular = scanner.nextLine();
                }
                case 7 -> {
                    System.out.print("Digite seu e-mail:");
                    email = scanner.nextLine();
                }
                case 8 -> {
                    System.out.print("Digite sua ocupação profissional:");
                    ocupacaoProfissional = scanner.nextLine();
                }
                case 9 -> {
                    System.out.print("Digite sua renda mensal:");
                    rendaMensal = scanner.nextLine();
                    while (!rendaMensal.matches("^\\d+$")) {
                        System.out.print("Por favor, digite somente números: ");
                        rendaMensal = scanner.nextLine();
                    }
                }
                case 0 -> System.out.println("Alterações salvas com sucesso!");
                default -> System.out.println("Opção inválida, tente novamente.");
            }

            // Imprime a informação alterada
            System.out.println("Alteração realizada: ");
            switch (opcao) {
                case 1 -> System.out.println("Nome completo: " + nomeCompleto);
                case 2 -> System.out.println("Apelido: " + apelido);
                case 3 -> System.out.println("Nacionalidade: " + nacionalidade);
                case 4 -> System.out.println("CPF: " + cpf);
                case 5 -> System.out.println("Data de nascimento: " + dataNascimento);
                case 6 -> System.out.println("Telefone ou celular: " + telefoneCelular);
                case 7 -> System.out.println("E-mail: " + email);
                case 8 -> System.out.println("Ocupação profissional: " + ocupacaoProfissional);
                case 9 -> System.out.println("Renda mensal: " + rendaMensal);
            }

            // Pergunta se o cliente deseja fazer mais alterações
            System.out.println("\nDeseja realizar mais alguma alteração? (S/N):");
            resposta = scanner.nextLine();

            // Verifica se a resposta diferente de "S" ou "N"
            while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")) {
                System.out.println("Opção inválida, Tente Novamente com S/N.");
                resposta = scanner.nextLine();
            }
        }
        // Exibir os termos e condições
        exibirTermosCondicoes();

        // Perguntar ao cliente se ele aceita os termos e condições
        System.out.println("\nVocê aceita os termos e condições? (S/N):");
        String aceitaTermos = scanner.nextLine();

        // Verificar se o cliente aceitou os termos
        if (aceitaTermos.equalsIgnoreCase("S")) {
            System.out.println("\nAbertura da conta digital em andamento...");
            System.out.println("========================================");
            System.out.println("Parabéns! Sua conta digital no DevBank foi aberta com sucesso!");
        } else {
            System.out.println("Não é possível abrir a conta digital sem aceitar os termos e condições.");
        }

        // Gera e salva o número da conta
        int numeroConta = gerarnumeroConta();
        System.out.println("Número da conta: " + formatarNumeroConta(numeroConta));

        // Solicita e salva a senha do cliente
        System.out.print("Crie uma senha com 6 dígitos: ");
        String senha = scanner.nextLine();

        // Verifica o tipo de cartão e limite de crédito conforme a renda mensal.
        String tipoCartao;
        int limiteCredito;

        if (Integer.parseInt(rendaMensal) <= 1199) {
            tipoCartao = "CLASSIC CARD";
            limiteCredito = 1000;
        } else if (Integer.parseInt(rendaMensal) <= 2999) {
            tipoCartao = "OCEAN CARD";
            limiteCredito = 3000;
        } else if (Integer.parseInt(rendaMensal) <= 7999) {
            tipoCartao = "SAPPHIRE CARD";
            limiteCredito = 5000;
        } else {
            tipoCartao = "BLACK CARD";
            limiteCredito = 10000;
        }
        System.out.println("\n========================================");
        System.out.println("Dados da sua conta:");
        System.out.println("========================================");
        System.out.println("\nNome: " + nomeCompleto);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefoneCelular);
        System.out.println("Numero da Conta: " + numeroConta);
        System.out.println("Senha: " + senha);
        System.out.println("Tipo de Cartão: " + tipoCartao);
        System.out.println("\nLimite de Crédito: " + limiteCredito);
        acessarConta();
    }

    public static int gerarnumeroConta() {
        int numeroContaAtual = 0; // Número da última conta aberta
        return numeroContaAtual + 1;
    }

    public static String formatarNumeroConta(int numeroConta) {
        return String.format("%06d-%03d", numeroConta / 1000, numeroConta % 1000);
    }

    public static void exibirTermosCondicoes() {
        System.out.println("Termos e Condições do DevBank");
        System.out.println("==============================");
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        System.out.println("Praesent vitae magna sed nunc maximus lacinia. Integer.");
        System.out.println("Sed ullamcorper leo sed tortor congue, vitae tempus justo.");
        System.out.println("Nulla facilisi. Duis nec mi et ante consequat iaculis at et ex.");
        System.out.println("Sed lobortis ipsum non malesuada volutpat.");
        System.out.println("Pellentesque fermentum ligula vitae venenatis vestibulum.");
        System.out.println("Quisque a enim ut quam feugiat volutpat.");
        System.out.println("Suspendisse potenti. Vestibulum at neque nec nunc facilisis finibus id nec mi.");
        System.out.println("Praesent ut facilisis ex. Nulla malesuada neque ut orci congue, et fermentum turpis auctor.");
        System.out.println("Fusce pharetra fermentum leo ut dapibus. In hac habitasse platea dictumst.");
        System.out.println("Sed mattis magna quis massa sollicitudin, eget bibendum ipsum fringilla.");
        System.out.println("Quisque non libero rutrum, condimentum dui ac, placerat odio.");
        System.out.println("===================================");
    }
}