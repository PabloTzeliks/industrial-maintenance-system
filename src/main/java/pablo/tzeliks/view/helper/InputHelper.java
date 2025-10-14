package pablo.tzeliks.view.helper;

import java.util.Scanner;

public class InputHelper {

    public static String lerString(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt + " ");

            String input = sc.nextLine().trim();

            if (input == null || input.isEmpty()) {
                MensagemHelper.erro("Valor não pode ser nulo!");
            } else {
                return input;
            }
        }
    }

    public static String lerStringPodeNull(Scanner sc, String prompt) {
        System.out.println(prompt + " ");
        return sc.nextLine().trim();
    }

    public static int lerInt(Scanner sc, String prompt) {
        while (true) {

            System.out.println(prompt + " ");

            String input = sc.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                MensagemHelper.erro("Valor inválido. Tente novamente!");
            }
        }
    }
}
