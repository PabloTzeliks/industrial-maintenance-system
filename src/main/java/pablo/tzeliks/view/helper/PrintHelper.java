package pablo.tzeliks.view.helper;

import pablo.tzeliks.model.Maquina;
import pablo.tzeliks.model.Tecnico;

import java.util.List;

public class PrintHelper {

    public static void printMaquina(Maquina maquina) {

        System.out.println("\n+------------------------------+\n");
        System.out.println(maquina.toString());
        System.out.println("\n+------------------------------+\n");
    }

    public static void printTecnico(Tecnico tecnico) {

        System.out.println("\n+------------------------------+\n");
        System.out.println(tecnico.toString());
        System.out.println("\n+------------------------------+\n");
    }

    public static void printListaMaquinas(List<Maquina> maquinas) {

        for (Maquina maquina : maquinas) {

            System.out.println(maquina.toString());

            System.out.println("\n+------------------------------+\n");
        }
    }

    public static void printListaTecnicos(List<Tecnico> tecnicos) {

        if (tecnicos.isEmpty()) {

            MensagemHelper.erro("Nenhum Técnico cadastrado no Sistema.");
        }

        for (Tecnico tecnico : tecnicos) {

            System.out.println(tecnico.toString());

            System.out.println("\n+------------------------------+\n");
        }
    }
}