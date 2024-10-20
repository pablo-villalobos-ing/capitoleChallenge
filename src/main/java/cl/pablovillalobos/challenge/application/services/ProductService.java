package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.ProductMapper;
import cl.pablovillalobos.challenge.application.usecase.GetProductByIdUseCase;
import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.adapter.ProductPersistenceAdapter;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements GetProductByIdUseCase {
    private final ProductPersistenceAdapter adapter;
    private final ProductMapper mapper;

    @Override
    public Product getProductById(Long id) throws DataAccessException {
        var product = adapter.findById(id);
        return product.map(mapper::entityToModel).orElse(null);
    }

    @Override
    public ProductEntity getProductEntityById(Long id) throws DataAccessException {
        return adapter.findById(id).orElse(null);
    }

    @Override
    public Boolean existProductById(Long id) throws DataAccessException {
        var product = adapter.findById(id);
        if (product.isPresent()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
