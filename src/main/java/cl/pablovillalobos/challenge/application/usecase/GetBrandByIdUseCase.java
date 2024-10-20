package cl.pablovillalobos.challenge.application.usecase;

import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public interface GetBrandByIdUseCase {
    Brand getBranById(Long id) throws DataAccessException;

    BrandEntity getBrandEntityById(Long id) throws DataAccessException;

    Boolean existsBrandById(Long id) throws DataAccessException;
}
