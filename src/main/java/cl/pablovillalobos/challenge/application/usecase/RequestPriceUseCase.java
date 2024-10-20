package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.exceptions.BrandNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.PriceNotFoundException;
import cl.pablovillalobos.challenge.infrastructure.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface RequestPriceUseCase {
    PriceResponseDto foundPrice(PriceRequestDto dto) throws DataAccessException, BrandNotFoundException, ProductNotFoundException, PriceNotFoundException;
}
