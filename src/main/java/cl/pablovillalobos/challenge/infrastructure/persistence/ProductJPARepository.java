package cl.pablovillalobos.challenge.infrastructure.persistence;

import cl.pablovillalobos.challenge.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
