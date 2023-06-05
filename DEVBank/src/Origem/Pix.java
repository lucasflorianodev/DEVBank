package Origem;


import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static Origem.ContaCorrente.extratocorrente;
import static Origem.ContaCorrente.saldocorrente;


public class Pix {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] chavesPix = new String[5]; // Array para armazenar as chaves PIX
    private static int contadorChaves = 0; // Contador de chaves cadastradas

    public static void exibirOpcoesPix() {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println(" PIX DEVBANK");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de operação PIX:");
            System.out.println("1 - Pagamento/Transferência");
            System.out.println("2 - Receber/Cobrança");
            System.out.println("3 - Cadastrar Chaves");
            System.out.println("4 - Minhas Chaves");
            System.out.println("0 - Encerrar operação e voltar para tela inicial");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) { // verifica se o próximo valor não é um número inteiro
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine(); // consome a entrada inválida
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> PagamentoTransferenciaPix();
                case 2 -> ReceberCobrancaPix();
                case 3 -> cadastraChaves();
                case 4 -> exibirMinhasChaves();
                case 0 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void PagamentoTransferenciaPix () {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n===============================");
            System.out.println(" PAGAMENTOS/TRANSFERÊNCIAS PIX ");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de pagamento:");
            System.out.println("1 - Chave (CPF, CNPJ, E-mail ou aleatória)");
            System.out.println("2 - Pix Copia e Cola");
            System.out.println("0 - Encerrar operação e voltar para Tela inicial Pix");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> chavePix();
                case 2 -> pixCopiaCola();
                case 3 -> sair = true;
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void chavePix () {
        if (contadorChaves == 0) {
            System.out.println("\nNenhuma chave PIX cadastrada.");
            System.out.println("Para utilizar qualquer serviço Pix, é necessário cadastrar pelo menos uma chave.");
            System.out.println("Você será direcionado para o cadastramento de Chaves Pix.");
            cadastraChaves();
            return;
        }
        System.out.print("Digite a chave PIX do destinatário: ");
        System.out.print("Digite o valor da transferência: ");
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Pagamento/Transferência não autorizada - Saldo insuficiente");
                return;

            } else {
                saldocorrente -= valor;

                LocalDateTime dataHora = LocalDateTime.now();
                String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Pagamento/Transferência Via Pix Chaves | Valor = R$ %.2f",
                        dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                        dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                        valor);
                extratocorrente.add(registro);
                System.out.println("\nProcessando Dados da Transferência");
                System.out.println("||||||||||||||");
                System.out.println("||||||||||||||||||||||||||||");
                System.out.printf("Pagamento/Transferência autorizada com Sucesso\n%s\n", registro);
            }

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            chavePix();
            return;
        }

        System.out.print("\nDeseja efetuar outro Pagamento/Transferência? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outro Pagamento/Transferência? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            chavePix();
        }
    }

    public static void pixCopiaCola () {
        if (contadorChaves == 0) {
            System.out.println("\nNenhuma chave PIX cadastrada.");
            System.out.println("Para utilizar qualquer serviço Pix, é necessário cadastrar pelo menos uma chave.");
            System.out.println("Você será direcionado para o cadastramento de Chaves Pix.");
            cadastraChaves();
            return;
        }
        System.out.print("Insira o código do Pix Copiar e Cola: ");
        System.out.print("Digite o valor da transferência: ");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            if (valor > saldocorrente) {
                System.out.println("Pagamento/Transferência não autorizada - Saldo insuficiente");
                return;

            } else {
                saldocorrente -= valor;

                LocalDateTime dataHora = LocalDateTime.now();
                String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Pagamento/Transferência Via Pix Copia e Cola | Valor = R$ %.2f",
                        dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                        dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                        valor);
                extratocorrente.add(registro);
                System.out.println("\nProcessando Dados da Transferência");
                System.out.println("||||||||||||||");
                System.out.println("||||||||||||||||||||||||||||");
                System.out.printf("Pagamento/Transferência autorizada com Sucesso\n%s\n", registro);
            }

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            chavePix();
            return;
        }

        System.out.print("\nDeseja efetuar outro Pagamento/Transferência? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outro Pagamento/Transferência? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            chavePix();
        }
    }

    public static void ReceberCobrancaPix () {
        if (contadorChaves == 0) {
            System.out.println("\nNenhuma chave PIX cadastrada.");
            System.out.println("Para utilizar qualquer serviço Pix, é necessário cadastrar pelo menos uma chave.");
            System.out.println("Você será direcionado para o cadastramento de Chaves Pix.");
            cadastraChaves();
            return;
        }
        System.out.print("Digite o valor que deseja receber/cobrar: ");

        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor <= 0) {
                System.out.println("Valor inválido, o valor a ser depositado deve ser maior que zero.");
                return;
            }

            String codigoAleatorio = gerarPixCopiaColaAleatorio();

            saldocorrente = valor;

            LocalDateTime dataHora = LocalDateTime.now();
            String registro = String.format("%02d:%02d:%02d - %02d/%02d/%04d | Receber/Cobrança Pix - Solicitação Aberta | Valor = R$ %.2f",
                    dataHora.getHour(), dataHora.getMinute(), dataHora.getSecond(),
                    dataHora.getDayOfMonth(), dataHora.getMonthValue(), dataHora.getYear(),
                    valor);
            extratocorrente.add(registro);
            System.out.println("\nProcessando Dados da Operação");
            System.out.println("||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||");
            System.out.printf("Receber Pix gerado com Sucesso\n%s\n", registro);
            System.out.println("Compartilhe o seguinte código abaixo.");
            System.out.println("Código Receber/Cobrança Pix: " + codigoAleatorio);

        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido, digite um valor numérico.");
            scanner.nextLine();
            ReceberCobrancaPix();
            return;
        }

        System.out.print("\nDeseja efetuar outra operação Receber Pix? (S/N): ");
        String novaOpcao = scanner.nextLine();

        while (!novaOpcao.equalsIgnoreCase("S") && !novaOpcao.equalsIgnoreCase("N")) {
            System.out.println("Opção inválida, tente novamente.");
            System.out.print("Deseja efetuar outra operação Receber Pix?? (S/N): ");
            novaOpcao = scanner.nextLine();
        }

        if (novaOpcao.equalsIgnoreCase("S")) {
            ReceberCobrancaPix();
        }
    }

    private static String gerarPixCopiaColaAleatorio() {
        String caracteresPermitidos = "0123456789";
        StringBuilder pix = new StringBuilder();

        // 20 caracteres antes
        for (int i = 0; i < 20; i++) {
            int randomIndex = new Random().nextInt(caracteresPermitidos.length());
            char randomChar = caracteresPermitidos.charAt(randomIndex);
            pix.append(randomChar);
        }

        pix.append("br.gov.bcb.pix1001qrcodes-pix.devbank.com.br/v4/b");

        // 58 caracteres depois
        for (int i = 0; i < 58; i++) {
            int randomIndex = new Random().nextInt(caracteresPermitidos.length());
            char randomChar = caracteresPermitidos.charAt(randomIndex);
            pix.append(randomChar);
        }

        pix.append("BR71822RECEBEDOR0000CIDADE");

        // 8 caracteres depois
        for (int i = 0; i < 8; i++) {
            int randomIndex = new Random().nextInt(caracteresPermitidos.length());
            char randomChar = caracteresPermitidos.charAt(randomIndex);
            pix.append(randomChar);
        }

        // 7 caracteres depois "***"
        pix.append("***");

        // 7 caracteres depois
        for (int i = 0; i < 7; i++) {
            int randomIndex = new Random().nextInt(caracteresPermitidos.length());
            char randomChar = caracteresPermitidos.charAt(randomIndex);
            pix.append(randomChar);
        }

        // Consultando e usando o valor atualizado do StringBuilder
        return pix.toString();
    }

    public static void cadastraChaves () {
        while (true) {
            System.out.println("\n===============================");
            System.out.println(" CADASTRO DE CHAVES ");
            System.out.println("===============================");
            System.out.println("\nSelecione o tipo de chave que deseja cadastrar:");
            System.out.println("1 - CPF");
            System.out.println("2 - CNPJ");
            System.out.println("3 - E-mail");
            System.out.println("4 - Chave Aleatória");
            System.out.println("0 - Voltar para Tela inicial Pix");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opção inválida, tente novamente.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                exibirOpcoesPix();
                break;
            }

            switch (opcao) {
                case 1 -> {
                    String cpf = "";
                    while (!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
                        System.out.print("Digite o CPF no formato correto (ex: 000.000.000-00): ");
                        cpf = scanner.nextLine();
                    }
                    // Lógica para cadastrar chave CPF
                    chavesPix[contadorChaves] = cpf;
                    contadorChaves++;
                    System.out.println("Chave CPF cadastrada com sucesso: " + cpf);
                }
                case 2 -> {
                    String cnpj = "";
                    while (!cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
                        System.out.print("Digite o CNPJ no formato correto (ex: 00.000.000/0000-00): ");
                        cnpj = scanner.nextLine();
                    }
                    // Lógica para cadastrar chave CNPJ
                    chavesPix[contadorChaves] = cnpj;
                    contadorChaves++;
                    System.out.println("Chave CNPJ cadastrada com sucesso: " + cnpj);
                }
                case 3 -> {
                    System.out.print("Digite o E-mail: ");
                    String email = scanner.nextLine();
                    // Lógica para cadastrar chave E-mail
                    chavesPix[contadorChaves] = email;
                    contadorChaves++;
                    System.out.println("Chave E-mail cadastrada com sucesso: " + email);
                }
                case 4 -> {
                    String chaveAleatoria = gerarChaveAleatoria();
                    // Lógica para cadastrar chave aleatória
                    chavesPix[contadorChaves] = chaveAleatoria;
                    contadorChaves++;
                    System.out.println("Chave Aleatória cadastrada com sucesso: " + chaveAleatoria);
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static String gerarChaveAleatoria () {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(32);

        for (int i = 0; i < 32; i++) {
            int index = random.nextInt(caracteres.length());
            char randomChar = caracteres.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static void exibirMinhasChaves () {
        System.out.println("\n===============================");
        System.out.println(" MINHAS CHAVES PIX ");
        System.out.println("===============================");

        if (contadorChaves == 0) {
            System.out.println("\nNenhuma chave PIX cadastrada.");
            System.out.println("Para utilizar qualquer serviço Pix, é necessário cadastrar pelo menos uma chave.");
            System.out.println("Você será direcionado para o cadastramento de Chaves Pix.");
            cadastraChaves();
        } else {
            System.out.println("Chaves PIX cadastradas:");

            for (int i = 0; i < contadorChaves; i++) {
                String tipoChave;

                if (chavesPix[i].matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
                    tipoChave = "CPF";
                } else if (chavesPix[i].matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")) {
                    tipoChave = "CNPJ";
                } else if (chavesPix[i].matches("^[a-z0-9]{32}$")) {
                    tipoChave = "Chave Aleatória";
                } else {
                    tipoChave = "E-mail";
                }
                System.out.println(tipoChave + ": " + chavesPix[i]);
            }

            boolean sair = false;
            while (!sair) {
                System.out.println("\nOpções:");
                System.out.println("1 - Voltar para Tela inicial Pix");
                System.out.println("2 - Encerrar operação e voltar para a tela inicial");
                System.out.print("Opção: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Opção inválida, tente novamente.");
                    scanner.nextLine();
                    continue;
                }

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> exibirOpcoesPix();
                    case 2 -> sair = true;
                    default -> System.out.println("Opção inválida, tente novamente.");
                }
                if (opcao == 0) {
                    break;
                }
            }
        }
    }
}
