package pablo.tzeliks.view.helper;

public class MenuHelper {

    public static void espacador() {

        System.out.println("=".repeat(50));
    }

    public static void menuPrincipal() {

        espacador();

        System.out.println("----- MENU PRINCIPAL -----");
        System.out.println("1- Cadastrar Máquina");
        System.out.println("2- Cadastrar Técnico");

        System.out.println("\n0- Sair do Sistema");

        System.out.println("\nDigite a opção desejada: ");
    }

    public static void menuCadastroMaquina() {

        espacador();

        System.out.println("----- CADASTRO MAQUINA -----");
    }

    public static void menuCadastroTecnico() {

        espacador();

        System.out.println("----- CADASTRO TÉCNICO -----");
    }
}