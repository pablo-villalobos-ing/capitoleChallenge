package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BrandMapperTest {
    @InjectMocks
    private BrandMapper mapper;

    @Test
    void testEntityToModel() {
        // Given
        BrandEntity brandEntity = BrandEntity.builder()
                .brandEntityId(1L)
                .name("Lorem Ipsum")
                .build();

        // When
        Brand brand = mapper.entityToModel(brandEntity);

        // Then
        assertNotNull(brand);
        assertEquals(brandEntity.getBrandEntityId(), brand.getId());
        assertEquals(brandEntity.getName(), brand.getName());
    }

    @Test
    void testModelToEntity() {
        // Given
        Brand brand = Brand.builder()
                .id(1L)
                .name("Lorem Ipsum")
                .build();

        // When
        BrandEntity brandEntity = mapper.modelToEntity(brand);

        // Then
        assertNotNull(brandEntity);
        assertEquals(brand.getId(), brandEntity.getBrandEntityId());
        assertEquals(brand.getName(), brandEntity.getName());
    }


}
