package pablo.tzeliks.application.service;

import pablo.tzeliks.application.dto.maquina.MaquinaRequestDTO;
import pablo.tzeliks.application.exception.MaquinaAlreadyExistsException;
import pablo.tzeliks.application.mapper.MaquinaMapper;
import pablo.tzeliks.application.service.contracts.MaquinaService;
import pablo.tzeliks.domain.model.entities.Maquina;
import pablo.tzeliks.domain.repository.MaquinaRepository;

public class MaquinaServiceImpl implements MaquinaService {

    private final MaquinaMapper maquinaMapper;
    private final MaquinaRepository maquinaRepository;

    public MaquinaServiceImpl(MaquinaMapper maquinaMapper, MaquinaRepository maquinaRepository) {
        this.maquinaMapper = maquinaMapper;
        this.maquinaRepository = maquinaRepository;
    }

    @Override
    public void registrarMaquina(MaquinaRequestDTO dto) {

        System.out.println("Registrando maquina");

        System.out.println("Maquina: " + dto.nome());

//        if (maquinaRepository.existsDuplicate(dto.nome(), dto.setor())) throw new MaquinaAlreadyExistsException("Essa máquina já existe nesse Setor.");
//
//        Maquina novaMaquina = maquinaMapper.toEntity(dto);
//
//        maquinaRepository.save(novaMaquina);
    }
}