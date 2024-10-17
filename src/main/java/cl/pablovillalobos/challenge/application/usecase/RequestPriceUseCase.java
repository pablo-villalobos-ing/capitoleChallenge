package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;

import java.util.Optional;

public interface RequestPriceUseCase {
    Optional<PriceResponseDto> foundPrice(PriceRequestDto dto);
}
