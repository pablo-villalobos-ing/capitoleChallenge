package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;

public interface GetProductByIdUseCase {
    Product getProductById(Long id);

    ProductEntity getProductEntityById(Long id);
}
