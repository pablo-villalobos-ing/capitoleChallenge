package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.persistence.ProductJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductPersistenceAdapterTest {
    @InjectMocks
    private ProductPersistenceAdapter productPersistenceAdapter;

    @Mock
    private ProductJPARepository productJPARepository;

    @Test
    void testFindById_WhenProductExists() throws DataAccessException {
        Long productId = 1L;

        ProductEntity mockedProductEntity = ProductEntity.builder()
                .productEntityId(1L)
                .name("Lorem Ipsum")
                .build();
        mockedProductEntity.setProductEntityId(productId);
        when(productJPARepository.findById(productId)).thenReturn(Optional.of(mockedProductEntity));
        Optional<ProductEntity> result = productPersistenceAdapter.findById(productId);

        assertTrue(result.isPresent());
        assertEquals(productId, result.get().getProductEntityId());
        verify(productJPARepository).findById(productId);
    }

    @Test
    void testFindById_WhenProductDoesNotExist() throws DataAccessException {
        Long productId = 1L;

        when(productJPARepository.findById(productId)).thenReturn(Optional.empty());
        Optional<ProductEntity> result = productPersistenceAdapter.findById(productId);

        assertFalse(result.isPresent());

        verify(productJPARepository).findById(productId);
    }
}
