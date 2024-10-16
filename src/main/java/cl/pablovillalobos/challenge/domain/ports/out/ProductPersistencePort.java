package cl.pablovillalobos.challenge.domain.ports.out;

import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;

import java.util.Optional;

public interface ProductPersistencePort {
    Optional<ProductEntity> findById(Long id);

}
