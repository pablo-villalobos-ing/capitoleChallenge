package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.usecase.RequestPriceUseCase;
import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.exceptions.BrandNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.PriceNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cl.pablovillalobos.challenge.infrastructure.util.Constants.BRAND_NOT_FOUND;
import static cl.pablovillalobos.challenge.infrastructure.util.Constants.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService implements RequestPriceUseCase {
    private final BrandService brandService;
    private final ProductService productService;
    private final PricePersistencePort pricePersistencePort;

    @Override
    public PriceResponseDto foundPrice(PriceRequestDto dto) throws DataAccessException, BrandNotFoundException, ProductNotFoundException, PriceNotFoundException {
        if (Boolean.FALSE.equals(brandService.existsBrandById(dto.getBrandId()))) {
            throw new BrandNotFoundException(BRAND_NOT_FOUND + dto.getBrandId());
        }
        if (Boolean.FALSE.equals(productService.existProductById(dto.getProductId()))) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND + dto.getProductId());
        }

        log.info("dto on Application layer");
        var price = pricePersistencePort.findByBrandIdAndDateAndProductId(dto);
        if (price.isPresent()) {
            return price.get();
        } else {
            log.info("price not found for this moment: " + dto.getDate().toString());
            throw new PriceNotFoundException("Price not found on database for this moment: " + dto.getDate().toString());
        }
    }
}
