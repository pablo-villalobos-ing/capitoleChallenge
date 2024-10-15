package cl.pablovillalobos.challenge.infrastructure.persistence;

import cl.pablovillalobos.challenge.infrastructure.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJPARepository extends JpaRepository<BrandEntity, Long> {
}
