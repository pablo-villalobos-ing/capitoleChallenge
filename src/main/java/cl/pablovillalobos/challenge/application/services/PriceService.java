package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.PriceMapper;
import cl.pablovillalobos.challenge.application.usecase.RequestPriceUseCase;
import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService implements RequestPriceUseCase {
    private final PriceMapper priceMapper;

    public PriceResponseDto foundPriceResponse(PriceRequestDto dto) {
        return priceMapper.modelToDto(foundPrice(dto));
    }


    @Override
    public Price foundPrice(PriceRequestDto dto) {
        return null;
    }
}
