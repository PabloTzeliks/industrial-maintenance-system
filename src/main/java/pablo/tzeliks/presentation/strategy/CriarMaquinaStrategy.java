package pablo.tzeliks.presentation.strategy;

import pablo.tzeliks.application.dto.maquina.MaquinaRequestDTO;
import pablo.tzeliks.application.service.contracts.MaquinaService;
import pablo.tzeliks.presentation.screen.CriarMaquinaScreen;

import java.util.Scanner;

public class CriarMaquinaStrategy implements MenuStrategy {

    private final MaquinaService maquinaService;

    public CriarMaquinaStrategy(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @Override
    public String getDescription() {
        return "Criar Máquina";
    }

    @Override
    public void execute(Scanner scanner) {

        CriarMaquinaScreen screen = new CriarMaquinaScreen();

        MaquinaRequestDTO requestDTO = screen.display(scanner);


    }
}
