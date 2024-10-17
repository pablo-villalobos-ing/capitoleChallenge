package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.application.mapper.PriceMapper;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.persistence.PriceJPARepository;
import cl.pablovillalobos.challenge.infrastructure.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTest {
    @InjectMocks
    private PricePersistenceAdapter pricePersistenceAdapter;

    @Mock
    private PriceJPARepository priceJPARepository;

    @Mock
    private PriceMapper priceMapper;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindByBrandIdAndDateAndProductId_WhenPricesExist() {
        PriceRequestDto requestDto = new PriceRequestDto();
        requestDto.setBrandId(1L);
        requestDto.setDate(LocalDateTime.of(2024, 10, 17, 10, 0, 0));
        requestDto.setProductId(2L);

        PriceEntity priceEntity1 = PriceEntity.builder()
                .brandEntity(null)
                .productEntity(null)
                .id(1L)
                .priority((short) 1)
                .priceList(1)
                .startDate(LocalDateTime.of(2024, 10, 10, 0, 0, 0))
                .endDate(LocalDateTime.of(2024, 10, 15, 0, 0, 0))
                .value(new BigDecimal("123.45"))
                .currency(Currency.EUR)
                .build();
        priceEntity1.setPriority((short) 1);
        PriceEntity priceEntity2 = PriceEntity.builder()
                .brandEntity(null)
                .productEntity(null)
                .id(2L)
                .priority((short) 3)
                .priceList(1)
                .startDate(LocalDateTime.of(2024, 10, 10, 0, 0, 0))
                .endDate(LocalDateTime.of(2024, 10, 15, 0, 0, 0))
                .value(new BigDecimal("435.45"))
                .currency(Currency.EUR)
                .build();

        List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2);


        when(priceJPARepository.findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId()))
                .thenReturn(priceEntities);


        PriceResponseDto expectedResponse = new PriceResponseDto();
        when(priceMapper.entityToDto(priceEntity2)).thenReturn(expectedResponse);


        Optional<PriceResponseDto> result = pricePersistenceAdapter.findByBrandIdAndDateAndProductId(requestDto);


        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
        verify(priceJPARepository).findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId());
        verify(priceMapper).entityToDto(priceEntity2);
    }

    @Test
    void testFindByBrandIdAndDateAndProductId_WhenNoPricesExist() {
        PriceRequestDto requestDto = new PriceRequestDto();
        requestDto.setBrandId(1L);
        requestDto.setDate(LocalDateTime.of(2024, 10, 17, 10, 0, 0));
        requestDto.setProductId(2L);

        when(priceJPARepository.findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId()))
                .thenReturn(List.of());

        Optional<PriceResponseDto> result = pricePersistenceAdapter.findByBrandIdAndDateAndProductId(requestDto);

        assertFalse(result.isPresent());

        verify(priceJPARepository).findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId());


        verify(priceMapper, never()).entityToDto(any());
    }

    @Test
    void testGetPrice_WhenPriceEntitiesExist() {
        PriceEntity priceEntity1 = new PriceEntity();
        priceEntity1.setPriority((short) 1);
        PriceEntity priceEntity2 = new PriceEntity();
        priceEntity2.setPriority((short) 2);

        List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2);
        PriceResponseDto expectedResponse = new PriceResponseDto();
        when(priceMapper.entityToDto(priceEntity2)).thenReturn(expectedResponse); // priceEntity2 tiene mayor prioridad

        Optional<PriceResponseDto> result = pricePersistenceAdapter.getPrice(priceEntities);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
        verify(priceMapper).entityToDto(priceEntity2);
    }

    @Test
    void testGetPrice_WhenNoPriceEntitiesExist() {
        Optional<PriceResponseDto> result = pricePersistenceAdapter.getPrice(List.of());

        assertFalse(result.isPresent());
        verify(priceMapper, never()).entityToDto(any());
    }
}
