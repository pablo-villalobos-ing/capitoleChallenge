package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.domain.ports.out.BrandPersistencePort;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.persistence.BrandJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandPersistenceAdapter implements BrandPersistencePort {
    private final BrandJPARepository repository;

    @Override
    public Optional<BrandEntity> findById(Long id) throws DataAccessException {
        return repository.findById(id);

    }
}
