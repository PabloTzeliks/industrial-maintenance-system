package pablo.tzeliks.view.helper;

public class MensagemHelper {

    public static void sucesso(String mensagem) {
        System.out.println("\n[SUCESSO] " + mensagem);
    }

    public static void erro(String mensagem) {
        System.out.println("\n[ERRO] " + mensagem);
    }

    public static void info(String mensagem) {
        System.out.println("\n[INFO] " + mensagem);
    }

    public static void subtitulo(String mensagem) {
        System.out.println("\n---" + mensagem + "---\n");
    }
}