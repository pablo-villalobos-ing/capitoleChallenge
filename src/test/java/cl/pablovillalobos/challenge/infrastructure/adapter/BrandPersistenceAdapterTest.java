package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.persistence.BrandJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandPersistenceAdapterTest {

    @InjectMocks
    private BrandPersistenceAdapter brandPersistenceAdapter;
    @Mock
    private BrandJPARepository brandJPARepository;


    @Test
    void testFindById_WhenBrandExists() {
        Long brandId = 1L;
        BrandEntity mockedBrandEntity = BrandEntity.builder()
                .brandEntityId(1L)
                .name("Lorem Ipsum")
                .build();
        when(brandJPARepository.findById(anyLong())).thenReturn(Optional.of(mockedBrandEntity));
        var result = brandPersistenceAdapter.findById(brandId);

        assertTrue(result.isPresent());
        assertEquals(brandId, result.get().getBrandEntityId());

        verify(brandJPARepository).findById(brandId);
    }

    @Test
    void testFindById_WhenBrandDoesNotExist() {
        Long brandId = 1L;
        when(brandJPARepository.findById(brandId)).thenReturn(Optional.empty());
        Optional<BrandEntity> result = brandPersistenceAdapter.findById(brandId);
        assertFalse(result.isPresent());
        verify(brandJPARepository).findById(brandId);
    }


}
