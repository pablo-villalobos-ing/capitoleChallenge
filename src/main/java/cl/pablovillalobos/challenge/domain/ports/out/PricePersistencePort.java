package cl.pablovillalobos.challenge.domain.ports.out;

import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;

import java.util.Optional;

public interface PricePersistencePort {
    Optional<PriceEntity> findById(Long id);
}
