package pablo.tzeliks.presentation;

import pablo.tzeliks.application.mapper.MaquinaMapper;
import pablo.tzeliks.application.service.MaquinaServiceImpl;
import pablo.tzeliks.application.service.contracts.MaquinaService;
import pablo.tzeliks.presentation.helper.InputHelper;
import pablo.tzeliks.presentation.helper.MenuHelper;
import pablo.tzeliks.presentation.strategy.CriarMaquinaStrategy;

import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner sc = new Scanner(System.in);
    private final MaquinaMapper mapper = MaquinaMapperImpl();
    private final MaquinaService maquinaService = new MaquinaServiceImpl(mapper);

    public void iniciar() {

        MenuHelper.menuPrincipal();

        int option = InputHelper.readInt(sc, "Digite a Opção desejada: ");

        while(true) {

            switch (option) {

                case 1:
                    CriarMaquinaStrategy.execute(sc, maquinaService);
            }
        }
    }
}
