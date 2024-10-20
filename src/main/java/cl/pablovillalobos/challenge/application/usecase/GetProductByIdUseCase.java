package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public interface GetProductByIdUseCase {
    Product getProductById(Long id) throws DataAccessException;

    ProductEntity getProductEntityById(Long id) throws DataAccessException;

    Boolean existProductById(Long id) throws DataAccessException;
}
