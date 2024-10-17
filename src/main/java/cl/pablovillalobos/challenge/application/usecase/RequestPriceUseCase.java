package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RequestPriceUseCase {
    Optional<PriceResponseDto> foundPrice(PriceRequestDto dto);
}
