package cl.pablovillalobos.challenge.domain.ports.out;

import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;

import java.util.Optional;

public interface BrandPersistencePort {

    Optional<BrandEntity> findById(Long id) throws DataAccessException;
}
