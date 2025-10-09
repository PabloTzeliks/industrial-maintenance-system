package pablo.tzeliks.application.mapper;

import pablo.tzeliks.application.dto.maquina.MaquinaRequestDTO;
import pablo.tzeliks.application.dto.tecnico.TecnicoRequestDTO;
import pablo.tzeliks.domain.model.entities.Maquina;

public interface MaquinaMapper {

    Maquina toEntity(MaquinaRequestDTO dto);
    MaquinaRequestDTO toDto(Maquina maquina);
}