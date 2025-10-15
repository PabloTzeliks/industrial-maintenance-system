package pablo.tzeliks.view.helper;

import pablo.tzeliks.model.Maquina;

import java.util.List;

public class PrintHelper {

    public static void printMaquina(Maquina maquina) {


        maquina.toString()
    }

    public static void printListaMaquinas(List<Maquina> maquinas) {

        if (maquinas.isEmpty()) {

            System.out.println("\n+------------------------------+\n");
            MensagemHelper.erro("Nenhuma Máquina cadastrada no Sistema.");
            System.out.println("\n+------------------------------+\n");
        }

        for (Maquina maquina : maquinas) {

            System.out.println(maquina.toString());

            System.out.println("\n+------------------------------+\n");
        }
    }
}