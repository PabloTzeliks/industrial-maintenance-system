package pablo.tzeliks;

import pablo.tzeliks.dao.MaquinaDAO;
import pablo.tzeliks.dao.OrdemManutencaoDAO;
import pablo.tzeliks.dao.PecaDAO;
import pablo.tzeliks.dao.TecnicoDAO;
import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.OrdemManutencao;
import pablo.tzeliks.model.Peca;
import pablo.tzeliks.model.Tecnico;
import pablo.tzeliks.model.enums.StatusMaquina;
import pablo.tzeliks.model.enums.StatusOrdemManutencao;
import pablo.tzeliks.view.helper.InputHelper;
import pablo.tzeliks.view.helper.MensagemHelper;
import pablo.tzeliks.view.helper.MenuHelper;
import pablo.tzeliks.view.helper.PrintHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MaquinaDAO maquinaDAO = new MaquinaDAO();
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        PecaDAO pecaDAO = new PecaDAO();
        OrdemManutencaoDAO ordemManutencaoDAO = new OrdemManutencaoDAO();

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
                case "4":
                    criarOrdemManutencao(sc, maquinaDAO, tecnicoDAO, ordemManutencaoDAO);
                    break;
                case "5":
                    associarPecasOrdens(sc, ordemManutencaoDAO);
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

        String nomeMaquina = InputHelper.lerString(sc, "Digite o nome do Máquina: ");
        String setorMaquina = InputHelper.lerString(sc, "Digite o nome do Setor: ");

        if (dao.existeMaquina(nomeMaquina, setorMaquina)) {

            MensagemHelper.erro("Máquina já Cadastrada neste Setor.");
            return;
        }

        Maquina maquina = new Maquina(0, nomeMaquina, setorMaquina, StatusMaquina.OPERACIONAL);

        dao.salvar(maquina);

        MensagemHelper.sucesso("Maquina Cadastrada com sucesso!");
    }

    public static void cadastrarTecnico(Scanner sc, TecnicoDAO dao) {

        sc.nextLine();

        MenuHelper.menuCadastroTecnico();

        String nomeTecnico = InputHelper.lerString(sc, "Digite o nome do Técnico: ");
        String especialidadeTecnico = InputHelper.lerStringPodeNull(sc, "Digite o nome da especialidade: ");

        if (dao.existeTecnico(nomeTecnico, especialidadeTecnico)) {

            MensagemHelper.erro("Técnico já Cadastrado no Sistema.");
            return;
        }

        Tecnico tecnico = new Tecnico(0, nomeTecnico, especialidadeTecnico);

        dao.salvar(tecnico);

        MensagemHelper.sucesso("Técnico Cadastrada com sucesso!");
    }

    public static void cadastrarPeca(Scanner sc, PecaDAO dao) {

        sc.nextLine();

        MenuHelper.menuCadastroPeca();

        String nomePeca = InputHelper.lerString(sc, "Digite o nome da Peça: ");
        double estoquePeca = InputHelper.lerDouble(sc, "Digite a quantidade do Estoque (Em Kg): ");

        if (estoquePeca <= 0) {

            MensagemHelper.erro("Quantidade de estoque da Peça deve ser maior que Zero.");
            return;
        }

        if (dao.existePeca(nomePeca)) {

            MensagemHelper.erro("Peça já cadastrada no Sistema.");
            return;
        }

        Peca peca = new Peca(0, nomePeca, estoquePeca);

        dao.salvar(peca);

        MensagemHelper.sucesso("Peça Cadastrada com sucesso!");
    }

    public static void criarOrdemManutencao(Scanner sc, MaquinaDAO maquinaDAO, TecnicoDAO tecnicoDAO, OrdemManutencaoDAO ordemDAO) {

        sc.nextLine();

        MenuHelper.menuCriarOrdemDeManutenao();

        // Listagem de Máquinas

        MensagemHelper.subtitulo("Listagem de Máquinas");

        List<Maquina> maquinas = maquinaDAO.listarMaquinasPorStatus(StatusMaquina.OPERACIONAL);

        if (maquinas.isEmpty()) {

            MensagemHelper.erro("Não há Máquinas no Sistema.");
            return;
        }

        PrintHelper.printListaMaquinas(maquinas);

        // Escolha da Máquina

        MensagemHelper.subtitulo("Escolha a Máquina");

        long inputMaquina = InputHelper.lerLong(sc, "Digite o ID da Máquina desejada: ");
        Optional<Maquina> maquinaOptional = maquinaDAO.buscarPorId(inputMaquina);

        Maquina maquinaSelecionada;

        if (maquinaOptional.isPresent()) {

            maquinaSelecionada = maquinaOptional.get();

            MensagemHelper.sucesso("Máquina com ID: " + inputMaquina + ", foi selecionada.");

            PrintHelper.printMaquina(maquinaSelecionada);
        } else {

            MensagemHelper.erro("Máquina com ID: " + inputMaquina + ", não encontrada. Tente novamente.");

            return;
        }

        // Listagem Técnicos

        MensagemHelper.subtitulo("Listagem de Técnicos");

        List<Tecnico> tecnicos = tecnicoDAO.listarTecnicos();

        if (tecnicos.isEmpty()) {

            MensagemHelper.erro("Não há Máquinas no Sistema.");
            return;
        }

        PrintHelper.printListaTecnicos(tecnicos);

        // Escolha do Técnico

        MensagemHelper.subtitulo("Escolha do Técnicos");

        long tecnicoId = InputHelper.lerLong(sc, "Digite o ID do técnico desejado: ");
        Optional<Tecnico> tecnico = tecnicoDAO.acharPorId(tecnicoId);

        Tecnico tecnicoSelecionado;

        if (tecnico.isPresent()) {

            tecnicoSelecionado = tecnico.get();

            MensagemHelper.sucesso("Técnico com ID: " + tecnicoId + ", foi selecionado.");

            PrintHelper.printTecnico(tecnicoSelecionado);
        } else {

            MensagemHelper.erro("Técnico com ID: " + tecnicoId + ", não encontrado. Tente novamente.");

            return;
        }

        // Construção da Ordem de Manutenção

        OrdemManutencao ordemManutencao = new OrdemManutencao(0, maquinaSelecionada, tecnicoSelecionado, StatusOrdemManutencao.PENDENTE);

        ordemDAO.salvar(ordemManutencao);

        // Mensagem final
        MensagemHelper.sucesso("Ordem de Manutenção Cadastrada com Sucesso!");
    }

    public static void associarPecasOrdens(Scanner sc, OrdemManutencaoDAO ordemManutencaoDAO) {

        MenuHelper.menuAssociacaoPecas();

        // Listagem de Ordens com Status Pendente

        List<OrdemManutencao> ordensManutencaoPendentes = new ArrayList<>();
        ordensManutencaoPendentes = ordemManutencaoDAO.listarOrdensPorStatus(StatusOrdemManutencao.PENDENTE);

        PrintHelper.printListaOrdensManutencao(ordensManutencaoPendentes);

        // Escolhe a Ordem de Manutenção




    }
}