package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.persistence.PriceJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PricePersistencePort {
    private final PriceJPARepository repository;

    @Override
    public Optional<PriceEntity> findById(Long id) {
        return repository.findById(id);
    }
}
