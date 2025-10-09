package pablo.tzeliks.presentation.screen;

import pablo.tzeliks.application.dto.maquina.MaquinaRequestDTO;
import pablo.tzeliks.domain.model.enums.Setor;
import pablo.tzeliks.domain.model.enums.StatusOperacional;
import pablo.tzeliks.presentation.helper.InputHelper;
import pablo.tzeliks.presentation.helper.MenuHelper;

import java.util.Scanner;

public class CriarMaquinaScreen {

    public MaquinaRequestDTO display(Scanner scanner) {

        MenuHelper.menuCriarMaquina();

        String nome = InputHelper.readString(scanner,"Digite o nome da Máquina: ");

        MenuHelper.mostrarSetores();

        Setor setor;

        while (true) {

            int escolhaSetor = InputHelper.readInt(scanner, "Digite sua escolha de Setor: ");

            switch (escolhaSetor) {

                case 1:
                    setor = Setor.PRODUCAO;
                    break;
                case 2:
                    setor = Setor.ENGENHARIA;
                    break;
                case 3:
                    setor = Setor.ADMINISTRATIVO;
                    break;
                case 4:
                    setor = Setor.LOGISTICA;
                    break;

                default:
                    System.out.println("Valor inválido, tente novamente.");
            }
        }

        return new MaquinaRequestDTO(
                nome,
                setor,
                StatusOperacional.EM_ANDAMENTO
        );
    }
}
