package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.ProductMapper;
import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.infrastructure.adapter.ProductPersistenceAdapter;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
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
class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductPersistenceAdapter adapter;
    @Mock
    private ProductMapper mapper;

    @Test
    public void getProductByIdTest() {
        var productEntity = getProductEntity();
        var product = getProduct();
        Mockito.when(adapter.findById(anyLong())).thenReturn(productEntity);
        Mockito.when(mapper.entityToModel(any())).thenReturn(product);
        var result = service.getProductById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void getProductByIdExpectedNull() {
        Mockito.when(adapter.findById(anyLong())).thenReturn(Optional.empty());
        var result = service.getProductById(1L);
        assertNull(result);
    }

    @Test
    void getProductEntityByIdTest() {
        var productEntity = getProductEntity();
        Mockito.when(adapter.findById(anyLong())).thenReturn(productEntity);
        var result = service.getProductEntityById(1L);
        assertEquals(1L, result.getProductEntityId());
    }

    @Test
    void getProductEntityByIdExpectedNull() {
        Mockito.when(adapter.findById(anyLong())).thenReturn(Optional.empty());
        var result = service.getProductEntityById(1L);
        assertNull(result);
    }

    private Optional<ProductEntity> getProductEntity() {
        return Optional.of(
                ProductEntity.builder()
                        .productEntityId(1L)
                        .name("Lorem Ipsum")
                        .build()
        );
    }

    private Product getProduct() {
        return Product.builder()
                .id(1L)
                .name("Lorem Ipsum")
                .build();
    }


}
