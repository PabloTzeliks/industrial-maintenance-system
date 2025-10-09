package pablo.tzeliks.presentation.helper;

import java.util.Scanner;

public class InputHelper {

    public static int readInt(Scanner sc, String prompt) {

        System.out.println(prompt);
        return sc.nextInt();
    }

    public static String readString(Scanner sc, String prompt) {

        System.out.println(prompt);
        return sc.nextLine();
    }
}