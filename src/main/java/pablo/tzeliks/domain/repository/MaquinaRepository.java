package pablo.tzeliks.domain.repository;

import pablo.tzeliks.domain.model.entities.Maquina;
import pablo.tzeliks.domain.model.enums.Setor;

import java.util.Optional;

public interface MaquinaRepository {

    public void save(Maquina maquina);

    public Optional<Maquina> findByName(String name);
    public Optional<Maquina> findById(long id);

    public boolean existsDuplicate(String name, Setor location);
}