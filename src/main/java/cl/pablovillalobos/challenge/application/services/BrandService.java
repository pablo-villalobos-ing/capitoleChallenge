package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.BrandMapper;
import cl.pablovillalobos.challenge.application.usecase.GetBrandByIdUseCase;
import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.adapter.BrandPersistenceAdapter;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import cl.pablovillalobos.challenge.infrastructure.exceptions.DataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService implements GetBrandByIdUseCase {
    private final BrandPersistenceAdapter adapter;
    private final BrandMapper mapper;

    @Override
    public Brand getBranById(Long id) throws DataAccessException {
        return mapper.entityToModel(adapter.findById(id).orElse(null));
    }

    @Override
    public BrandEntity getBrandEntityById(Long id) throws DataAccessException {
        return adapter.findById(id).orElse(null);
    }

    @Override
    public Boolean existsBrandById(Long id) throws DataAccessException {
        var brand = adapter.findById(id);
        if (brand.isPresent()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
