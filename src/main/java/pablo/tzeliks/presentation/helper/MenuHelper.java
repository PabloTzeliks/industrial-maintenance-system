package pablo.tzeliks.presentation.helper;

public class MenuHelper {

    public static void menuPrincipal() {

        System.out.println("--- Menu Principal ---");

        System.out.println("\n[1] Cadastrar Máquina");
        // ...
    }

    // Menus de Opções do Sistema

    public static void menuCriarMaquina() {

        System.out.println("--- Criar Máquina ---");
    }

    // Utilitários

    public static void mostrarSetores() {

        System.out.println();
        System.out.println("[1] Produção");
        System.out.println("[2] Engenharia");
        System.out.println("[3] Administrativo");
        System.out.println("[4] Logística");
    }
}
