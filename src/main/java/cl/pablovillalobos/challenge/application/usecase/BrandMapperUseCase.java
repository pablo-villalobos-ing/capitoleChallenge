package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;

public interface BrandMapperUseCase {
    Brand entityToModel(BrandEntity brand);

    BrandEntity modelToEntity(Brand brand);
}
