package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.domain.model.Product;
import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.BrandNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.PriceNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.ProductNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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

    private static PriceRequestDto getPriceRequestDto() {
        return PriceRequestDto.builder()
                .brandId(1L)
                .productId(1L)
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    void foundPriceWithBrandNull() throws DataAccessException, ProductNotFoundException, PriceNotFoundException {
        var dto = getDto();
        var priceRequestDto = getPriceRequestDto();
        Mockito.when(brandService.existsBrandById(anyLong())).thenReturn(Boolean.FALSE);
        var exception = assertThrows(BrandNotFoundException.class, () -> {
            service.foundPrice(dto);
        });
        assertEquals("Brand not found on database with id: 1", exception.getMessage());
        verify(pricePersistencePort, never()).findByBrandIdAndDateAndProductId(any());
    }

    @Test
    void foundPriceWithProductNull() throws DataAccessException, BrandNotFoundException, ProductNotFoundException, PriceNotFoundException {
        var dto = getDto();
        var priceRequestDto = getPriceRequestDto();
        Mockito.when(brandService.existsBrandById(anyLong())).thenReturn(Boolean.TRUE);
        Mockito.when(productService.existProductById(anyLong())).thenReturn(Boolean.FALSE);
        var exception = assertThrows(ProductNotFoundException.class, () -> {
            service.foundPrice(priceRequestDto);
        });
        assertEquals("Product not found on database with id:1", exception.getMessage());
        verify(pricePersistencePort, never()).findByBrandIdAndDateAndProductId(any());

    }

    @Test
    void foundPriceTest() throws DataAccessException, BrandNotFoundException, ProductNotFoundException, PriceNotFoundException {
        var dto = getDto();
        Mockito.when(brandService.existsBrandById(anyLong())).thenReturn(Boolean.TRUE);
        Mockito.when(productService.existProductById(anyLong())).thenReturn(Boolean.TRUE);
        Mockito.when(pricePersistencePort.findByBrandIdAndDateAndProductId(any())).thenReturn(getResponse());
        var result = service.foundPrice(dto);

        assertEquals(1, result.getPriceList());
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
