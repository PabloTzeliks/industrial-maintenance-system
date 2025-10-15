package pablo.tzeliks.view.helper;

import pablo.tzeliks.model.Maquina;

import java.util.List;

public class PrintHelper {

    public static void printMaquina(Maquina maquina) {

        System.out.println("\n+------------------------------+\n");
        System.out.println(maquina.toString());
        System.out.println("\n+------------------------------+\n");
    }

    public static void printListaMaquinas(List<Maquina> maquinas) {

        if (maquinas.isEmpty()) {

            MensagemHelper.erro("Nenhuma Máquina cadastrada no Sistema.");
        }

        for (Maquina maquina : maquinas) {

            System.out.println(maquina.toString());

            System.out.println("\n+------------------------------+\n");
        }
    }
}