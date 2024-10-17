package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.application.services.BrandService;
import cl.pablovillalobos.challenge.application.services.ProductService;
import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import cl.pablovillalobos.challenge.infrastructure.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {
    @InjectMocks
    private PriceMapper priceMapper;

    @Mock
    private BrandService brandService;

    @Mock
    private ProductService productService;

    private static Price getPrice() {
        return Price.builder()
                .id(1L)
                .value(new BigDecimal("100.00"))
                .currency(Currency.EUR)
                .startDate(LocalDateTime.of(2024, 10, 17, 0, 0, 0))
                .endDate(LocalDateTime.of(2024, 10, 17, 0, 0, 0))
                .priceList(1)
                .productId(1L)
                .brandId(1L)
                .build();
    }

    private static PriceEntity getPriceEntity(ProductEntity productEntity, BrandEntity brandEntity) {
        return PriceEntity.builder()
                .id(1L)
                .value(new BigDecimal("100.00"))
                .currency(Currency.EUR)
                .startDate(LocalDateTime.of(2024, 10, 17, 0, 0, 0))
                .endDate(LocalDateTime.of(2024, 10, 17, 0, 0, 0))
                .priceList(1)
                .productEntity(productEntity)
                .brandEntity(brandEntity)
                .priority((short) 1)
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testModelToEntity() {
        // Given
        ProductEntity mockedProductEntity = ProductEntity.builder().productEntityId(1L).build();
        BrandEntity mockedBrandEntity = BrandEntity.builder().brandEntityId(1L).build();
        Price price = getPrice();

        // Mocking external dependencies
        when(productService.getProductEntityById(1L)).thenReturn(mockedProductEntity);
        when(brandService.getBrandEntityById(1L)).thenReturn(mockedBrandEntity);

        // When
        PriceEntity priceEntity = priceMapper.modelToEntity(price);

        // Then
        assertNotNull(priceEntity);
        assertEquals(price.getId(), priceEntity.getId());
        assertEquals(price.getValue(), priceEntity.getValue());
        assertEquals(price.getCurrency(), priceEntity.getCurrency());
        assertEquals(price.getStartDate(), priceEntity.getStartDate());
        assertEquals(price.getEndDate(), priceEntity.getEndDate());
        assertEquals(mockedProductEntity, priceEntity.getProductEntity());
        assertEquals(mockedBrandEntity, priceEntity.getBrandEntity());

        // Verify interactions
        verify(productService).getProductEntityById(1L);
        verify(brandService).getBrandEntityById(1L);
    }

    @Test
    void testEntityToModel() {
        // Given
        ProductEntity productEntity = ProductEntity.builder().productEntityId(1L).build();
        BrandEntity brandEntity = BrandEntity.builder().brandEntityId(1L).build();
        PriceEntity priceEntity = getPriceEntity(productEntity, brandEntity);

        // When
        Price price = priceMapper.entityToModel(priceEntity);

        // Then
        assertNotNull(price);
        assertEquals(priceEntity.getId(), price.getId());
        assertEquals(priceEntity.getValue(), price.getValue());
        assertEquals(priceEntity.getCurrency(), price.getCurrency());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(productEntity.getProductEntityId(), price.getProductId());
        assertEquals(brandEntity.getBrandEntityId(), price.getBrandId());
    }

    @Test
    void testModelToDto() {
        // Given
        Price price = getPrice();

        // When
        PriceResponseDto priceDto = priceMapper.modelToDto(price);

        // Then
        assertNotNull(priceDto);
        assertEquals(price.getBrandId(), priceDto.getBrandId());
        assertEquals(price.getProductId(), priceDto.getProductId());
        assertEquals(price.getStartDate(), priceDto.getStartDate());
        assertEquals(price.getEndDate(), priceDto.getEndDate());
        assertEquals(price.getValue(), priceDto.getValue());
    }

    @Test
    void testEntityToDto() {
        // Given
        ProductEntity productEntity = ProductEntity.builder().productEntityId(1L).build();
        BrandEntity brandEntity = BrandEntity.builder().brandEntityId(1L).build();
        PriceEntity priceEntity = getPriceEntity(productEntity, brandEntity);

        // When
        PriceResponseDto priceDto = priceMapper.entityToDto(priceEntity);

        // Then
        assertNotNull(priceDto);
        assertEquals(priceEntity.getBrandEntity().getBrandEntityId(), priceDto.getBrandId());
        assertEquals(priceEntity.getProductEntity().getProductEntityId(), priceDto.getProductId());
        assertEquals(priceEntity.getStartDate(), priceDto.getStartDate());
        assertEquals(priceEntity.getEndDate(), priceDto.getEndDate());
        assertEquals(priceEntity.getValue(), priceDto.getValue());
    }

}
