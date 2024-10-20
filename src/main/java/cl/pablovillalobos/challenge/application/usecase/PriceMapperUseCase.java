package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public interface PriceMapperUseCase {
    PriceEntity modelToEntity(Price price, BrandEntity brand, ProductEntity product);

    Price entityToModel(PriceEntity priceEntity);

    PriceResponseDto modelToDto(Price price);

    PriceResponseDto entityToDto(PriceEntity price);

}
