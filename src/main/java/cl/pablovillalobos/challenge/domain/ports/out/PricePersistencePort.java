package cl.pablovillalobos.challenge.domain.ports.out;

import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;

import java.util.Optional;

public interface PricePersistencePort {
    Optional<PriceResponseDto> findByBrandIdAndDateAndProductId(PriceRequestDto dto);

}
