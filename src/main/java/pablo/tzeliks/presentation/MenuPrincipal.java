package pablo.tzeliks.presentation;

import pablo.tzeliks.presentation.helper.InputHelper;
import pablo.tzeliks.presentation.helper.MenuHelper;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {

        MenuHelper.menuPrincipal();

        int option = InputHelper.readInt(sc, "Digite a Opção desejada: ");

        while(true) {

            switch (option) {

                case 1:

            }
        }
    }
}
