package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;

public interface RequestPriceUseCase {
    Price foundPrice(PriceRequestDto dto);
}
