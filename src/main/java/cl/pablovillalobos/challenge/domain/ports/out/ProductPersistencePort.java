package cl.pablovillalobos.challenge.domain.ports.out;

import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;

import java.util.Optional;

public interface ProductPersistencePort {
    Optional<ProductEntity> findById(Long id) throws DataAccessException;

}
