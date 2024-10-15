package cl.pablovillalobos.challenge.infrastructure.persistence;

import cl.pablovillalobos.challenge.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceJPARepository extends JpaRepository<PriceEntity, Long> {
}
