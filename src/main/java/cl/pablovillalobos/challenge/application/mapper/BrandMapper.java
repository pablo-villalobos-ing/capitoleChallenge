package cl.pablovillalobos.challenge.application.mapper;

import cl.pablovillalobos.challenge.application.usecase.BrandMapperUseCase;
import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandMapper implements BrandMapperUseCase {
    @Override
    public Brand entityToModel(BrandEntity brand) {
        return Brand.builder()
                .id(brand.getBrandEntityId())
                .name(brand.getName())
                .build();
    }

    @Override
    public BrandEntity modelToEntity(Brand brand) {
        return BrandEntity.builder()
                .brandEntityId(brand.getId())
                .name(brand.getName())
                .build();
    }
}
