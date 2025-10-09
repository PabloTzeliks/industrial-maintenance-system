package pablo.tzeliks.application.dto.maquina;

import pablo.tzeliks.domain.model.enums.Setor;
import pablo.tzeliks.domain.model.enums.StatusOperacional;

public record MaquinaRequestDTO(

        String nome,
        Setor setor,
        StatusOperacional status
) { }