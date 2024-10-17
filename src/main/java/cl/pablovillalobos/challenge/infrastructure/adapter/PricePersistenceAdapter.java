package cl.pablovillalobos.challenge.infrastructure.adapter;

import cl.pablovillalobos.challenge.application.mapper.PriceMapper;
import cl.pablovillalobos.challenge.domain.ports.out.PricePersistencePort;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceRequestDto;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.persistence.PriceJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricePersistenceAdapter implements PricePersistencePort {
    private final PriceJPARepository repository;
    private final PriceMapper mapper;


    @Override
    public Optional<PriceResponseDto> findByBrandIdAndDateAndProductId(PriceRequestDto dto) {
        log.info("dto processed, sent to repository method");
        var pricesList = repository.findByBrandIdAndDateAndProductId(dto.getBrandId(), dto.getDate(), dto.getProductId());
        log.info("Size result of query: " + pricesList.size());
        return getPrice(pricesList);
    }

    protected Optional<PriceResponseDto> getPrice(List<PriceEntity> priceEntities) {
        log.info("Filtering by priority");
        return priceEntities.stream().max(Comparator.comparing(PriceEntity::getPriority)).map(mapper::entityToDto);
    }


}
