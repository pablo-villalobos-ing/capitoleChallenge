package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;

public interface GetBrandByIdUseCase {
    Brand getBranById(Long id);

    BrandEntity getBrandEntityById(Long id);
}
