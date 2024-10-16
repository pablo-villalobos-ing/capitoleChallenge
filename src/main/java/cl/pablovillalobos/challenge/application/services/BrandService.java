package cl.pablovillalobos.challenge.application.services;

import cl.pablovillalobos.challenge.application.mapper.BrandMapper;
import cl.pablovillalobos.challenge.application.usecase.GetBrandByIdUseCase;
import cl.pablovillalobos.challenge.domain.model.Brand;
import cl.pablovillalobos.challenge.infrastructure.adapter.BrandPersistenceAdapter;
import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService implements GetBrandByIdUseCase {
    private final BrandPersistenceAdapter adapter;
    private final BrandMapper mapper;

    @Override
    public Brand getBranById(Long id) {
        return mapper.entityToModel(adapter.findById(id).orElse(null));
    }

    @Override
    public BrandEntity getBrandEntityById(Long id) {
        return adapter.findById(id).orElse(null);
    }
}
