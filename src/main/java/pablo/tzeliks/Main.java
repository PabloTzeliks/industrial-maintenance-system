package pablo.tzeliks;

import pablo.tzeliks.dao.MaquinaDAO;
import pablo.tzeliks.dao.PecaDAO;
import pablo.tzeliks.dao.TecnicoDAO;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.Peca;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.model.enums.StatusMaquina;
import pablo.tzeliks.view.helper.InputHelper;
import pablo.tzeliks.view.helper.MensagemHelper;
import pablo.tzeliks.view.helper.MenuHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MaquinaDAO maquinaDAO = new MaquinaDAO();
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        PecaDAO pecaDAO = new PecaDAO();

        while (true) {
            MenuHelper.menuPrincipal();

            String opcao = sc.next();

            switch (opcao) {

                case "1":
                    cadastrarMaquina(sc, maquinaDAO);
                    break;
                case "2":
                    cadastrarTecnico(sc, tecnicoDAO);
                    break;
                case "3":
                    cadastrarPeca(sc, pecaDAO);
                    break;
                case "0":
                    MensagemHelper.info("Saindo...");
                    return;

                default:
                    MensagemHelper.erro("Erro! Input inválido, tente novamente.");
            }
        }
    }

    public static void cadastrarMaquina(Scanner sc, MaquinaDAO dao) {

        sc.nextLine();

        MenuHelper.menuCadastroMaquina();

        String nomeMaquina = InputHelper.lerString(sc, "Digite o nome do maquina: ");
        String setorMaquina = InputHelper.lerString(sc, "Digite o nome do setor: ");

        if (dao.existeMaquina(nomeMaquina, setorMaquina)) {

            MensagemHelper.erro("Máquina já cadastrada neste Setor.");
            return;
        }

        Maquina maquina = new Maquina(0, nomeMaquina, setorMaquina, StatusMaquina.OPERACIONAL);

        dao.save(maquina);

        MensagemHelper.sucesso("Maquina Cadastrada com sucesso!");
    }

    public static void cadastrarTecnico(Scanner sc, TecnicoDAO dao) {

        sc.nextLine();

        MenuHelper.menuCadastroTecnico();

        String nomeTecnico = InputHelper.lerString(sc, "Digite o nome do tecnico: ");
        String especialidadeTecnico = InputHelper.lerStringPodeNull(sc, "Digite o nome da especialidade: ");

        if (dao.existeTecnico(nomeTecnico, especialidadeTecnico)) {

            MensagemHelper.erro("Técnico já cadastrado no Sistema.");
            return;
        }

        Tecnico tecnico = new Tecnico(0, nomeTecnico, especialidadeTecnico);

        dao.save(tecnico);

        MensagemHelper.sucesso("Tecnico Cadastrada com sucesso!");
    }

    public static void cadastrarPeca(Scanner sc, PecaDAO dao) {

        sc.nextLine();

        MenuHelper.menuCadastroTecnico();

        String nomePeca = InputHelper.lerString(sc, "Digite o nome do tecnico: ");
        String estoquePeca = InputHelper.lerStringPodeNull(sc, "Digite o nome da especialidade: ");



        Peca peca = new Peca();

        dao.save(peca);

        MensagemHelper.sucesso("Peça Cadastrada com sucesso!");
    }
}