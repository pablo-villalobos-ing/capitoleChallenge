package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    @InjectMocks
    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper();
    }

    @Test
    void testEntityToModel() {
        // Given
        ProductEntity productEntity = ProductEntity.builder()
                .productEntityId(1L)
                .name("Laptop")
                .build();

        // When
        Product product = mapper.entityToModel(productEntity);

        // Then
        assertNotNull(product);
        assertEquals(productEntity.getProductEntityId(), product.getId());
        assertEquals(productEntity.getName(), product.getName());
    }

    @Test
    void testModelToEntity() {
        // Given
        Product product = Product.builder()
                .id(1L)
                .name("Smartphone")
                .build();

        // When
        ProductEntity productEntity = mapper.modelToEntity(product);

        // Then
        assertNotNull(productEntity);
        assertEquals(product.getId(), productEntity.getProductEntityId());
        assertEquals(product.getName(), productEntity.getName());
    }
}
