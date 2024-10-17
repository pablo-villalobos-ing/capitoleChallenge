package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapperUseCase {
    Product entityToModel(ProductEntity product);

    ProductEntity modelToEntity(Product product);
}
