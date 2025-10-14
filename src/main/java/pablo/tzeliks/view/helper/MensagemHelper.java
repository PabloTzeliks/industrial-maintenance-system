package pablo.tzeliks.view.helper;

public class MensagemHelper {

    public static void sucesso(String mensagem) {
        System.out.println("[SUCESSO] " + mensagem);
    }

    public static void erro(String mensagem) {
        System.out.println("[ERRO] " + mensagem);
    }

    public static void info(String mensagem) {
        System.out.println("[INFO] " + mensagem);
    }
}