package pablo.tzeliks.application.dto.maquina;

import pablo.tzeliks.domain.model.enums.StatusOperacional;

public record MaquinaRequestDTO(

        String nome,
        String setor,
        StatusOperacional status
) { }