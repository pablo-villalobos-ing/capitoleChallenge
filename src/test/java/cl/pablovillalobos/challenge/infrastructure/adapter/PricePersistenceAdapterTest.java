package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.application.mapper.PriceMapper;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.persistence.PriceJPARepository;
import cl.pablovillalobos.challenge.infrastructure.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    private static Stream<Arguments> getControlCase() {
        return Stream.of(
                Arguments.of(getDto(LocalDateTime.of(2020, 6, 14, 10, 0, 0)),
                        List.of(getPriceEntity(LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                (short) 0, new BigDecimal("35.50"), 1)),
                        getResponse(LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 0, new BigDecimal("35.50"))
                ),

                Arguments.of(getDto(LocalDateTime.of(2020, 6, 14, 16, 0, 0)),
                        List.of(getPriceEntity(LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                        (short) 0, new BigDecimal("35.50"), 1),
                                getPriceEntity(LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                        LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                                        (short) 1, new BigDecimal("25.45"), 2)
                        ),
                        getResponse(LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                LocalDateTime.of(2020, 6, 14, 18, 30, 0), 1, new BigDecimal("25.45"))),

                Arguments.of(
                        getDto(LocalDateTime.of(2020, 6, 14, 21, 0, 0)),
                        List.of(getPriceEntity(LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                        (short) 0, new BigDecimal("35.50"), 1),
                                getPriceEntity(LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                        LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                                        (short) 1, new BigDecimal("25.45"), 2)
                        ),
                        getResponse(LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                LocalDateTime.of(2020, 6, 14, 18, 30, 0), 1, new BigDecimal("25.45"))),
                Arguments.of(
                        getDto(LocalDateTime.of(2020, 6, 15, 10, 0, 0)),
                        List.of(getPriceEntity(LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 15, 11, 0, 0),
                                (short) 1, new BigDecimal("30.50"), 1)
                        ),
                        getResponse(LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 15, 11, 0, 0), 3, new BigDecimal("30.50"))),
                Arguments.of(
                        getDto(LocalDateTime.of(2020, 6, 15, 21, 0, 0)),
                        List.of(getPriceEntity(LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                        LocalDateTime.of(2020, 12, 15, 11, 0, 0),
                                        (short) 1, new BigDecimal("30.50"), 1),
                                getPriceEntity(LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                        (short) 1, new BigDecimal("30.50"), 4)
                        ),
                        getResponse(LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59), 4, new BigDecimal("38.95")))
        );
    }

    private static PriceResponseDto getResponse(LocalDateTime startDate, LocalDateTime endDate, Integer priceList, BigDecimal value) {
        return PriceResponseDto.builder()
                .productId(35455L)
                .brandId(1L)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .value(value)
                .build();
    }

    private static PriceRequestDto getDto(LocalDateTime date) {
        return PriceRequestDto.builder()
                .brandId(1L)
                .productId(35455L)
                .date(date)
                .build();
    }

    @Test
    void testGetPrice_WhenNoPriceEntitiesExist() {
        Optional<PriceResponseDto> result = pricePersistenceAdapter.getPrice(List.of());

        assertFalse(result.isPresent());
        verify(priceMapper, never()).entityToDto(any());
    }

    private static PriceEntity getPriceEntity(LocalDateTime startDate,
                                              LocalDateTime endDate,
                                              Short priority,
                                              BigDecimal value, Integer priceList) {
        return PriceEntity.builder()
                .id((long) (Math.random() * 10000))
                .value(value)
                .currency(Currency.EUR)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .priority(priority)
                .brandEntity(getBrand())
                .productEntity(getProduct())
                .build();
    }

    private static BrandEntity getBrand() {
        return BrandEntity.builder()
                .brandEntityId(1L)
                .name("Lorem Ipsum")
                .build();
    }

    private static ProductEntity getProduct() {
        return ProductEntity.builder()
                .productEntityId(35455L)
                .name("Lorem ipsum")
                .build();
    }

    @Test
    void testFindByBrandIdAndDateAndProductId_WhenPricesExist() {
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .brandId(1L)
                .productId(2L)
                .date(LocalDateTime.of(2024, 10, 17, 10, 0, 0))
                .build();

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
        PriceRequestDto requestDto = PriceRequestDto.builder()
                .brandId(1L)
                .productId(2L)
                .date(LocalDateTime.of(2024, 10, 17, 10, 0, 0))
                .build();
        when(priceJPARepository.findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId()))
                .thenReturn(List.of());
        Optional<PriceResponseDto> result = pricePersistenceAdapter.findByBrandIdAndDateAndProductId(requestDto);

        assertFalse(result.isPresent());
        verify(priceJPARepository).findByBrandIdAndDateAndProductId(requestDto.getBrandId(), requestDto.getDate(), requestDto.getProductId());
        verify(priceMapper, never()).entityToDto(any());
    }

    @Test
    void testGetPrice_WhenPriceEntitiesExist() {
        PriceEntity priceEntity1 = PriceEntity.builder().priority((short) 1).build();
        PriceEntity priceEntity2 = PriceEntity.builder().priority((short) 2).build();

        List<PriceEntity> priceEntities = List.of(priceEntity1, priceEntity2);
        PriceResponseDto expectedResponse = new PriceResponseDto();
        when(priceMapper.entityToDto(priceEntity2)).thenReturn(expectedResponse);

        Optional<PriceResponseDto> result = pricePersistenceAdapter.getPrice(priceEntities);

        assertTrue(result.isPresent());
        assertEquals(expectedResponse, result.get());
        verify(priceMapper).entityToDto(priceEntity2);
    }

    @ParameterizedTest
    @MethodSource("getControlCase")
    void controlCaseTest(PriceRequestDto dto, List<PriceEntity> listPriceEntity, PriceResponseDto expected) {
        when(priceJPARepository.findByBrandIdAndDateAndProductId(eq(1L), eq(dto.getDate()), eq(35455L)))
                .thenReturn(listPriceEntity);
        when(priceMapper.entityToDto(any())).thenReturn(expected);
        var result = pricePersistenceAdapter.findByBrandIdAndDateAndProductId(dto);
        assertEquals(expected.getValue(), result.get().getValue());
    }
}
