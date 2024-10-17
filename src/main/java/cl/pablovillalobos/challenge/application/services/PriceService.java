package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.usecase.RequestPriceUseCase;
import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService implements RequestPriceUseCase {
    private final PricePersistencePort pricePersistencePort;

    @Override
    public Optional<PriceResponseDto> foundPrice(PriceRequestDto dto) {
        log.info("dto on Application layer");
        return pricePersistencePort.findByBrandIdAndDateAndProductId(dto);
    }
}
