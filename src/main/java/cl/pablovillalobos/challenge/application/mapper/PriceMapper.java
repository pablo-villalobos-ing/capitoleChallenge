package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.application.usecase.PriceMapperUseCase;
import cl.pablovillalobos.challenge.domain.model.Price;
import cl.pablovillalobos.challenge.infrastructure.controllers.dto.PriceResponseDto;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceMapper implements PriceMapperUseCase {

    @Override
    public PriceEntity modelToEntity(Price price, BrandEntity brand, ProductEntity product) {
        return PriceEntity.builder()
                .id(price.getId())
                .value(price.getValue())
                .currency(price.getCurrency())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .productEntity(product)
                .brandEntity(brand)
                .build();
    }

    @Override
    public Price entityToModel(PriceEntity priceEntity) {
        return Price.builder()
                .id(priceEntity.getId())
                .value(priceEntity.getValue())
                .currency(priceEntity.getCurrency())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priceList(priceEntity.getPriceList())
                .productId(priceEntity.getProductEntity().getProductEntityId())
                .brandId(priceEntity.getBrandEntity().getBrandEntityId())
                .priority(priceEntity.getPriority())
                .build();
    }

    @Override
    public PriceResponseDto modelToDto(Price price) {
        return PriceResponseDto.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .value(price.getValue())
                .build();
    }

    @Override
    public PriceResponseDto entityToDto(PriceEntity price) {
        return PriceResponseDto.builder()
                .brandId(price.getBrandEntity().getBrandEntityId())
                .productId(price.getProductEntity().getProductEntityId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .value(price.getValue())
                .build();
    }
}
