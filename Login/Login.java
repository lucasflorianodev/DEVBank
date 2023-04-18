import java.util.Scanner;

public class Login {

    public static void main(String[] args) {

        String usuario, senha;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu usuário: ");
        usuario = scanner.nextLine();

        System.out.println("Digite sua senha: ");
        senha = scanner.nextLine();

        if (usuario.equals("lulufloris") && senha.equals("123456")) {
            System.out.println("Login bem sucedido!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }

        scanner.close();
    }

}





