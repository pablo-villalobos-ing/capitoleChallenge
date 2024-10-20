package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.domain.ports.out.ProductPersistencePort;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.persistence.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {
    private final ProductJPARepository repository;

    @Override
    public Optional<ProductEntity> findById(Long id) throws DataAccessException {
        return repository.findById(id);
    }
}
