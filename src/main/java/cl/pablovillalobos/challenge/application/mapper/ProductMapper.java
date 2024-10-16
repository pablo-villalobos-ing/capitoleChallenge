package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.application.usecase.ProductMapperUseCase;
import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper implements ProductMapperUseCase {

    @Override
    public Product entityToModel(ProductEntity product) {
        return Product.builder()
                .id(product.getProductEntityId())
                .name(product.getName())
                .build();
    }

    @Override
    public ProductEntity modelToEntity(Product product) {
        return ProductEntity.builder()
                .productEntityId(product.getId())
                .name(product.getName())
                .build();
    }
}
