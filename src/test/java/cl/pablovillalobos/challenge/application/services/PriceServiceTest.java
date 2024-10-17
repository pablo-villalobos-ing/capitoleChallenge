package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
    @InjectMocks
    private PriceService service;
    @Mock
    private BrandService brandService;
    @Mock
    private ProductService productService;
    @Mock
    private PricePersistencePort pricePersistencePort;

    @Test
    void foundPriceWithBrandNull() {
        var dto = getDto();
        Mockito.when(brandService.getBrandEntityById(anyLong())).thenReturn(null);
        var result = service.foundPrice(dto);
        assertTrue(result.isEmpty());
    }

    @Test
    void foundPriceWithProductNull() {
        var dto = getDto();
        Mockito.when(brandService.getBrandEntityById(anyLong())).thenReturn(getBrandEntity());
        Mockito.when(productService.getProductById(anyLong())).thenReturn(null);
        var result = service.foundPrice(dto);
        assertTrue(result.isEmpty());

    }

    @Test
    void foundPriceTest() {
        var dto = getDto();
        Mockito.when(brandService.getBrandEntityById(anyLong())).thenReturn(getBrandEntity());
        Mockito.when(productService.getProductById(anyLong())).thenReturn(getProduct());
        Mockito.when(pricePersistencePort.findByBrandIdAndDateAndProductId(any())).thenReturn(getResponse());
        var result = service.foundPrice(dto);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getPriceList());
    }

    private Optional<PriceResponseDto> getResponse() {
        return Optional.of(PriceResponseDto.builder()
                .value(new BigDecimal("123.45"))
                .priceList(1)
                .endDate(LocalDateTime.now())
                .startDate(LocalDateTime.now())
                .productId(1L)
                .brandId(1L)
                .build());
    }

    private PriceRequestDto getDto() {
        return PriceRequestDto.builder()
                .brandId(1L)
                .productId(1L)
                .date(LocalDateTime.of(2024, 10, 17, 0, 12, 0))
                .build();
    }

    private BrandEntity getBrandEntity() {
        return BrandEntity.builder()
                .brandEntityId(1L)
                .name("Lorem Ipsum")
                .build();
    }

    private Product getProduct() {
        return Product.builder()
                .id(1L)
                .name("Lorem Ipsum")
                .build();
    }

}
