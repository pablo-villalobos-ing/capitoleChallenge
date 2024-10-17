package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.BrandMapper;
import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.adapter.BrandPersistenceAdapter;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {
    @InjectMocks
    private BrandService service;

    @Mock
    private BrandPersistenceAdapter adapter;

    @Mock
    private BrandMapper mapper;

    @Test
    void getBrandByIdTest() {
        var brand = getBrandData();
        var brandEntity = getBrandEntity();
        Mockito.when(adapter.findById(anyLong())).thenReturn(brandEntity);
        Mockito.when(mapper.entityToModel(any())).thenReturn(brand);
        var result = service.getBranById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void getBrandByIdExpectedNull() {
        Mockito.when(adapter.findById(1L)).thenReturn(Optional.empty());
        var result = service.getBranById(1L);
        assertNull(result);

    }

    @Test
    void getBrandEntityByIdTest() {
        var brandEntity = getBrandEntity();
        Mockito.when(adapter.findById(anyLong())).thenReturn(brandEntity);
        var result = service.getBrandEntityById(1L);
        assertEquals(1L, result.getBrandEntityId());

    }

    @Test
    void getBrandEntityByIdExpectedNull() {
        Mockito.when(adapter.findById(1L)).thenReturn(Optional.empty());
        var result = service.getBrandEntityById(1L);
        assertNull(result);

    }

    private Optional<BrandEntity> getBrandEntity() {
        return Optional.of(BrandEntity.builder()
                .brandEntityId(1L)
                .name("Lorem Ipsum")
                .build());
    }

    private Brand getBrandData() {
        return Brand.builder()
                .id(1L)
                .name("Lorem Ipsum")
                .build();
    }

}
