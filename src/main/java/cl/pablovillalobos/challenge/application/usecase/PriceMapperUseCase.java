package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;

public interface PriceMapperUseCase {
    PriceEntity modelToEntity(Price price);

    Price entityToModel(PriceEntity priceEntity);

    PriceResponseDto modelToDto(Price price);

}
