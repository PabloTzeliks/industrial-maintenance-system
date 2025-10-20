package pablo.tzeliks.view.helper;

public class MenuHelper {

    public static void espacador() {

        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    public static void menuPrincipal() {

        espacador();

        System.out.println("----- MENU PRINCIPAL -----");
        System.out.println("1- Cadastrar Máquina");
        System.out.println("2- Cadastrar Técnico");
        System.out.println("3- Cadastrar Peça");
        System.out.println("4- Criar Ordem de Manutenção");
        System.out.println("5- Associar Peças a uma Ordem");

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

    public static void menuCadastroPeca() {

        espacador();

        System.out.println("----- CADASTRO PEÇA -----");
    }

    public static void menuCriarOrdemDeManutenao() {

        espacador();

        System.out.println("----- CRIAR ORDEM DE MANUTENÇÃO -----");
    }

    public static void menuAssociacaoPecas() {

        espacador();

        System.out.println("----- ASSOCIAR PEÇAS À ORDEM -----");
    }

    public static void menuExecucaoDeManutencao() {

        espacador();

        System.out.println("----- EXECUTAR MANUTENÇÃO -----");
    }
}