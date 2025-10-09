package pablo.tzeliks.presentation.strategy;

import pablo.tzeliks.application.dto.maquina.MaquinaRequestDTO;
import pablo.tzeliks.application.service.contracts.MaquinaService;
import pablo.tzeliks.presentation.screen.CriarMaquinaScreen;

import java.util.Scanner;

public class CriarMaquinaStrategy implements MenuStrategy {

    @Override
    public String getDescription() {
        return "Criar Máquina";
    }

    public static void execute(Scanner scanner, MaquinaService maquinaService) {

        CriarMaquinaScreen screen = new CriarMaquinaScreen();

        MaquinaRequestDTO requestDTO = screen.display(scanner);

        maquinaService.registrarMaquina(requestDTO);
    }
}